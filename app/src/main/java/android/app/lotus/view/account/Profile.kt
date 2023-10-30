package android.app.lotus.view.account

import android.accounts.Account
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp)
                .padding(horizontal = 25.dp)
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                text = "Account"
            )

            ProfileComponent()
        }
        SettingsMenuComponent(navController, profileViewModel)
    }
}

@Composable
private fun ProfileComponent() {
    Column() {
        // Username
        app.currentUser?.customDataAsBsonDocument()?.get("username")?.asString()?.value?.let {
            Text(
                it,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Other details
        Column {
            app.currentUser?.customDataAsBsonDocument()?.get("role")?.asString()?.value?.let {
                ProfileDetailItem("Role", it)
            }
            app.currentUser?.customDataAsBsonDocument()?.get("company")?.asString()?.value?.let {
                ProfileDetailItem("Company", it)
            }
            app.currentUser?.customDataAsBsonDocument()?.get("email")?.asString()?.value?.let {
                ProfileDetailItem("Email", it)
            }
            app.currentUser?.customDataAsBsonDocument()?.get("phone")?.asInt64()?.value?.let {
                ProfileDetailItem("Phone", it.toString())
            }
        }
    }
}

@Composable
private fun ProfileDetailItem(title: String, content: String) {
    Text(
        style = MaterialTheme.typography.bodyMedium,
        lineHeight = 30.sp,
        text = "$title: $content",
    )
}


@Composable
private fun SettingsMenuComponent(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    Column(
        modifier = Modifier.padding(bottom = 100.dp)
    ) {
        NavButton(
            "Edit Profile",
            suffixIcon = Icons.Rounded.Edit,
            navController = navController,
            route = ""
        )
        NavButton(
            "Support",
            suffixIcon = Icons.Rounded.Support,
            navController = navController,
            route = Routes.support
        )

        if (app.currentUser?.customDataAsBsonDocument()?.get("role")
                ?.asString()?.value == UserRole.HR.displayName
        ) {
            NavButton(
                "Create Account",
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