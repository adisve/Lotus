package android.app.lotus.view.navgraph

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.account.Profile
import android.app.lotus.view.account.Support
import android.app.lotus.view.account.hr.CreateAccount
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AccountNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    ) {
    NavHost(navController = navController, startDestination = Routes.profile) {
        profile(navController, profileViewModel)
        createUserAccount(profileViewModel, navController)
        support(navController)
    }
}

private fun NavGraphBuilder.profile(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
) {
    composable(Routes.profile) {
        Profile(navController, profileViewModel)
    }
}
private fun NavGraphBuilder.createUserAccount(profileViewModel: ProfileViewModel, navController: NavHostController) {
    composable(Routes.profileAddUserAccount) {
        CreateAccount(profileViewModel, navController)
    }
}

private fun NavGraphBuilder.support(navController: NavHostController) {
    composable(Routes.profileSupport) {
        Support(navController)
    }
}


