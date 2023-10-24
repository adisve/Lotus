package android.app.lotus.view.account

import android.app.lotus.app
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.view.buttons.NavButton
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument

@Composable
fun Profile(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (modifier = Modifier.padding(top = 150.dp)) {
        }
        SettingsMenuComponent(navController, profileViewModel)
    }
}

@Composable
private fun SettingsMenuComponent(navController: NavHostController, profileViewModel: ProfileViewModel) {
    Column(
        modifier = Modifier.padding(bottom = 100.dp)
    ) {
        NavButton("Edit Profile", suffixIcon = Icons.Rounded.Edit, navController = navController, route = "")
        NavButton("Support", suffixIcon = Icons.Rounded.Support, navController = navController, route = Routes.support)

        if (app.currentUser?.customDataAsBsonDocument()?.get("role")?.asString()?.value == UserRole.HR.displayName) {
            NavButton("Create Account", suffixIcon = Icons.Rounded.PersonAdd, navController = navController, route = Routes.createUserAccount)
        }

        LogOutButton {
            profileViewModel.logOut()
            navController.navigate(Routes.home)
        }
    }
}

@Composable
private fun LogOutButton(logOut: () -> Unit) {
    Button(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        onClick = logOut
    ) {
        Row (
            modifier = Modifier.padding((7.5).dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Log Out",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Rounded.ExitToApp,
                contentDescription = null
            )
        }
    }
}