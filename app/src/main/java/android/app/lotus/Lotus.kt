package android.app.lotus

import android.annotation.SuppressLint
import android.app.lotus.data.AuthStatus
import android.app.lotus.observables.AuthViewModel
import android.app.lotus.view.auth.Login
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.general.TopBar
import android.app.lotus.view.navgraph.SetupAuthNavGraph
import android.app.lotus.view.navgraph.SetupMainNavGraph
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus() {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authStatus by authViewModel.authStatus.collectAsState(initial = AuthStatus.Loading)

    when (authStatus) {
        AuthStatus.Success -> {
            val navController = rememberNavController()
            Scaffold (
                topBar = { TopBar() },
                bottomBar = { BottomNavigation(
                    navController = navController
                )
                }
            ) {
                SetupMainNavGraph(navController)
            }
        }
        AuthStatus.Unauthorized -> {
            val navController = rememberNavController()
            SetupAuthNavGraph(navController, authViewModel)
        }
        AuthStatus.Loading -> { DotsPulsing() }
    }

}