package android.app.lotus.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.navgraph.SetupNavGraph
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus() {
    val navController = rememberNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        SetupNavGraph(navController) // Setup navigation routes
        BottomNavigation(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
