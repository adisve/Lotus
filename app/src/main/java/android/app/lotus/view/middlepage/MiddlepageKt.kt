package android.app.lotus.view.middlepage

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ProfileViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun MiddlePage(navController: NavHostController,
               profileViewModel: ProfileViewModel
) {
    var userRole = getRole(profileViewModel)

    when (userRole) {
        "HR" -> navController.navigate(Routes.stats)
        "Manager" -> navController.navigate(Routes.support)
        "Employee" -> navController.navigate(Routes.support)
        else -> {
            // Handle the case when userRole doesn't match any of the specified roles
            // You can show an error message or navigate to a default screen.
        }
    }
}

fun  getRole(profileViewModel: ProfileViewModel): String{
    var userRole = profileViewModel.getUserRoleAsString()
    return userRole
}