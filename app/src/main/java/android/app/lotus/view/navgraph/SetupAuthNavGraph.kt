package android.app.lotus.view.navgraph

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.AuthViewModel
import android.app.lotus.view.auth.Login
import android.app.lotus.view.auth.RegisterAccount
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupAuthNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = Routes.login) {
        login(navController, authViewModel)
        register(navController, authViewModel)
    }
}

private fun NavGraphBuilder.login(navController: NavHostController, authViewModel: AuthViewModel) {
    composable(Routes.login) {
        Login(navController, authViewModel)
    }
}

private fun NavGraphBuilder.register(navController: NavHostController, authViewModel: AuthViewModel) {
    composable(Routes.register) {
        RegisterAccount(navController, authViewModel)
    }
}