package android.app.lotus.view.home.articles

import android.app.lotus.app
import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.domain.models.realm.article
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleListStatus
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.utils.getUserProperty
import android.app.lotus.view.general.DotsPulsing
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReadMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.realm.kotlin.mongodb.User

@Composable
fun Articles(navController: NavHostController, articleViewModel: ArticleViewModel) {
    val status by articleViewModel.status.observeAsState(initial = ArticleListStatus.Loading)
    val managerArticles by articleViewModel.managerArticleList.observeAsState(initial = listOf())
    val employeeArticles by articleViewModel.employeeArticleList.observeAsState(initial = listOf())
    val user = app.currentUser!!

    when (status) {
        ArticleListStatus.Loading -> DotsPulsing()
        ArticleListStatus.Empty -> Text("Nothing here yet")
        ArticleListStatus.Populated -> RoleBasedArticles(navController, user, managerArticles, employeeArticles)
    }
}

@Composable
fun RoleBasedArticles(
    navController: NavHostController,
    user: User,
    managerArticles: List<article>,
    employeeArticles: List<article>
) {
    val userRole = getUserProperty(user, UserFields.role)
    Log.d("Articles", "User role: $userRole")

    when (userRole) {
        UserRole.MANAGER.displayName -> ScrollableArticleList(navController, "Manager Articles", managerArticles)
        UserRole.EMPLOYEE.displayName -> ScrollableArticleList(navController, "Employee Articles", employeeArticles)
        UserRole.HR.displayName -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                    .padding(bottom = 100.dp)
            ) {
                item {
                    Text(
                        "Employee Articles",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                items(employeeArticles) { article ->
                    ArticleCard(navController, article)
                }
                item {
                    Text(
                        "Manager Articles",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp, top = 25.dp)
                    )
                }
                items(managerArticles) { article ->
                    ArticleCard(navController, article)
                }
            }
        }
    }
}

@Composable
fun ScrollableArticleList(navController: NavHostController, header: String, articles: List<article>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp)
            .padding(bottom = 100.dp)
    ) {
        item {
            Text(header, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))
        }
        items(articles) { article ->
            ArticleCard(navController, article)
        }
    }
}

@Composable
fun ArticleCard(navController: NavHostController, article: article) {
    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 5.dp, vertical = 10.dp)) {
        Card {
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    headlineColor = MaterialTheme.colorScheme.onSurface,
                    supportingColor = MaterialTheme.colorScheme.onSurface,
                ),
                headlineContent = {
                    Text(
                        article.title,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )},
                trailingContent = {
                    Icon(
                        Icons.Filled.ReadMore,
                        contentDescription = "Localized description",
                    )
                },
                modifier = Modifier.clickable {
                    navController.navigate(Routes.homeArticleDetail.replace("{title}", article.title))
                }
            )
        }
    }
}