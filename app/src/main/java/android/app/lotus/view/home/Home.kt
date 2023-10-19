import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.home.articles.ArticleListScreen
import android.app.lotus.view.home.articles.ArticleView
import android.app.lotus.view.home.videos.VideoView
import android.app.lotus.view.home.videos.VideosScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Routes.articles) }) {
            Text(text = "Articles", fontSize = 22.sp, modifier = Modifier.padding(5.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Routes.videos) }) {
            Text(text = "Videos", fontSize = 22.sp, modifier = Modifier.padding(5.dp))
        }
    }
}

