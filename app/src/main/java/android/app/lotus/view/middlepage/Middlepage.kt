package android.app.lotus.view.middlepage

import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.home.Home
import android.app.lotus.view.statistics.Stats
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun MiddlePage(navController: NavHostController,
               profileViewModel: ProfileViewModel
) {
    var userRole = getRole(profileViewModel)

    when (userRole) {
        "HR" -> Stats(navController = navController)
        "Manager" -> Stats(navController = navController) // Manger Questiona
        "Employee" -> Stats(navController = navController) //Employee Questions
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