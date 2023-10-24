package android.app.lotus

import android.annotation.SuppressLint
import android.app.lotus.data.services.AuthStatus
import android.app.lotus.observables.HomeViewModel
import android.app.lotus.observables.MainViewModel
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.auth.Login
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.general.TopBar
import android.app.lotus.view.navgraph.SetupNavgraph
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus(mainViewModel: MainViewModel, isDarkTheme: Boolean) {
    val authStatus by mainViewModel.authStatus.collectAsState(initial = AuthStatus.Loading)
    val navController = rememberNavController()

    when (authStatus) {
        AuthStatus.Success -> {
            Scaffold (
                topBar = { TopBar(isDarkTheme = isDarkTheme, updateTheme = { isDarkTheme ->
                    mainViewModel.updateTheme(isDarkTheme)
                })},
                bottomBar = { BottomNavigation(navController) }
            ) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val profileViewModel: ProfileViewModel = hiltViewModel()
                SetupNavgraph(
                    navController,
                    profileViewModel,
                    homeViewModel,
                )
            }
        }
        AuthStatus.Unauthorized -> {
            Login(mainViewModel = mainViewModel)
        }
        AuthStatus.Loading -> { DotsPulsing() }
    }
}