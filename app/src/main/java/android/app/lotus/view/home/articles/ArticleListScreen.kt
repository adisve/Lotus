package android.app.lotus.view.home.articles

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleListStatus
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.buttons.NavButton
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Articles(navController: NavHostController) {

    val articleViewModel: ArticleViewModel = hiltViewModel()
    val articleCategories = articleViewModel.articleCategories.observeAsState(initial = emptyList())
    val status = articleViewModel.status.observeAsState(initial = ArticleListStatus.Loading)


    when (status.value) {
        ArticleListStatus.Loading -> {
            DotsPulsing()
        }

        ArticleListStatus.Empty -> {
            Text("Empty")
        }

        ArticleListStatus.Populated -> {
            ScrollableList(navController, articleCategories = articleCategories.value)
        }
    }

}

@Composable
fun ScrollableList(navController: NavHostController, articleCategories: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(articleCategories) { category ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavButton(
                    text = category,
                    navController = navController,
                    route = Routes.articleDetail.replace("{articleName}", category),
                    suffixIcon = null
                )
            }

        }
    }
}