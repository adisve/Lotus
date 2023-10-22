package android.app.lotus.view.navgraph

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.MainViewModel
import android.app.lotus.view.auth.Login
import android.app.lotus.view.auth.RegisterAccount
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupAuthNavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Routes.login) {
        login(navController, mainViewModel)
        register(navController, mainViewModel)
    }
}

private fun NavGraphBuilder.login(navController: NavHostController, mainViewModel: MainViewModel) {
    composable(Routes.login) {
        Login(navController, mainViewModel)
    }
}

private fun NavGraphBuilder.register(navController: NavHostController, mainViewModel: MainViewModel) {
    composable(Routes.register) {
        RegisterAccount(navController, mainViewModel)
    }
}