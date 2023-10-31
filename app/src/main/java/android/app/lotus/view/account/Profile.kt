package android.app.lotus.view.account

import android.app.lotus.app
import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.utils.getUserProperty
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import io.realm.kotlin.mongodb.User

@Composable
fun Profile(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
) {
    val user = app.currentUser!!
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp)
                .padding(horizontal = 25.dp)
        ) {
            ProfileCard(user)
        }
        SettingsMenuComponent(navController, profileViewModel, user)
    }
}

@Composable
private fun ProfileCard(user: User) {
Card(
    modifier = Modifier
        .fillMaxWidth(),
    shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Text(
                text = getUserProperty(user, UserFields.username),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = getUserProperty(user, UserFields.email),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Department: ${getUserProperty(user, UserFields.company).capitalize()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Role: ${getUserProperty(user, UserFields.role).capitalize()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SettingsMenuComponent(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    user: User
) {
    Column(
        modifier = Modifier.padding(bottom = 100.dp)
    ) {
        NavButton(
            "Support",
            suffixIcon = Icons.Rounded.Support,
            navController = navController,
            route = Routes.support
        )

        if (getUserProperty(user, UserFields.role) == UserRole.HR.displayName
        ) {
            NavButton(
                "Add an account",
                suffixIcon = Icons.Rounded.PersonAdd,
                navController = navController,
                route = Routes.createUserAccount
            )
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
        Row(
            modifier = Modifier.padding((7.5).dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Log out",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
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