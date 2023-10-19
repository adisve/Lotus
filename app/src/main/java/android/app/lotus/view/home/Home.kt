import android.app.lotus.domain.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

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

