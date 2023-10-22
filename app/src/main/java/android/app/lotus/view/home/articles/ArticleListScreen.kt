package android.app.lotus.view.home.articles

import android.app.lotus.observables.ArticleListStatus
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Articles(navController: NavHostController) {

    val articleViewModel: ArticleViewModel = hiltViewModel()
    val articleCategories = articleViewModel.articleCategories.observeAsState(initial = emptyList())
    val status = articleViewModel.status.observeAsState(initial = ArticleListStatus.Loading)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (status.value) {
            ArticleListStatus.Loading -> { DotsPulsing() }
            ArticleListStatus.Empty -> { Text("Empty") }
            ArticleListStatus.Populated -> { ScrollableList(articleCategories = articleCategories.value) }
        }
    }
}

@Composable
fun ScrollableList(articleCategories: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(articleCategories) { category ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val buttonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

                Button(
                    onClick = {
                        //Code
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = buttonColors
                ) {
                    Text(
                        text = category,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }
}