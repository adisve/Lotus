package android.app.lotus.view.home.articles

import android.app.lotus.observables.ArticleViewModel
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Articles(navController: NavHostController) {

    var articleViewModel: ArticleViewModel = hiltViewModel()
    val articleCategories = articleViewModel.getArticleCategories()
    var accountType = "bakgrund"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 70.dp)


    ) {
        ScrollableList(articleCategories = articleCategories, accountType)
    }
}

@Composable
fun ScrollableList(articleCategories: List<String>, accountType: String) {
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