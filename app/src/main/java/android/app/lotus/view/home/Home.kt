package android.app.lotus.view.home

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.buttons.NavButton
import androidx.compose.foundation.layout.*
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().padding(top = 100.dp).padding(horizontal = 25.dp)
        ) {
            Text(
                fontSize = 23.sp,
                text = "Welcome!"
            )
            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                fontSize = 23.sp,
                text = "Below you will find links to articles and videos that act as educational resources for employees."
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(bottom = 150.dp)
        ) {
            NavButton(
                text = "Articles",
                navController = navController,
                route = Routes.articles
            )
            NavButton(
                text = "Videos",
                navController = navController,
                route = Routes.videos
            )
        }
    }
}




