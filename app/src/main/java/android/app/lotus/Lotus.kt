package android.app.lotus

import android.annotation.SuppressLint
import android.app.lotus.data.AuthStatus
import android.app.lotus.observables.MainViewModel
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.general.TopBar
import android.app.lotus.view.navgraph.SetupAuthNavGraph
import android.app.lotus.view.navgraph.SetupMainNavGraph
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus(mainViewModel: MainViewModel, isDarkTheme: Boolean) {
    val authStatus by mainViewModel.authStatus.collectAsState(initial = AuthStatus.Loading)
    val mainNavController = rememberNavController()
    val authNavController = rememberNavController()

    when (authStatus) {
        AuthStatus.Success -> {
            Scaffold (
                topBar = { TopBar(isDarkTheme = isDarkTheme, updateTheme = { isDarkTheme ->
                    mainViewModel.updateTheme(isDarkTheme)
                })},
                bottomBar = { BottomNavigation(mainNavController) }
            ) {
                SetupMainNavGraph(mainNavController)
            }
        }
        AuthStatus.Unauthorized -> {
            SetupAuthNavGraph(authNavController, mainViewModel)
        }
        AuthStatus.Loading -> { DotsPulsing() }
    }
}