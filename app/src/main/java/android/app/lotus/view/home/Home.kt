import android.app.lotus.domain.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
        NavButton(
            text = "Articles",
            modifier = Modifier.width(175.dp),
            navController = navController,
            route = Routes.articles
        )
        Spacer(modifier = Modifier.height(20.dp))
        NavButton(
            text = "Videos",
            modifier = Modifier.width(175.dp),
            navController = navController,
            route = Routes.videos
        )
    }
}


@Composable
fun NavButton(
    text: String,
    modifier: Modifier,
    navController: NavHostController,
    route: String) {
    Button(
        modifier = modifier,
        onClick = { navController.navigate(route) }
    ) {
        Text(text = text, fontSize = 22.sp, modifier = Modifier.padding(5.dp))
        Icon(imageVector = Icons.Rounded.ArrowRight, contentDescription = "right pointing arrow")
    }
}