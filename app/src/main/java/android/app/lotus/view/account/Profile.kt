package android.app.lotus.view.account

import android.app.lotus.observables.ProfileViewModel
import androidx.compose.foundation.clickable
import io.realm.kotlin.mongodb.User
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import org.mongodb.kbson.BsonString
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Profile(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val user by profileViewModel.user.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingsMenuComponent(profileViewModel)
        ProfileDescriptionComponent(user!!)
    }
}

@Composable
private fun ProfileDescriptionComponent(user: User) {
    val email = (user.customDataAsBsonDocument()?.get("email") as? BsonString)?.value ?: "Unknown"
    Text(
        text = "Email: $email",
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
private fun SettingsMenuComponent(profileViewModel: ProfileViewModel) {
    Column(
        modifier = Modifier.padding(top = 75.dp)
    ) {
        SettingsItem("Edit Profile", Icons.Rounded.Edit) { /*TO DO*/ }
        SettingsItem("Advice & Support", Icons.Rounded.Support) { /*TO DO*/ }
        SettingsItem("Log Out", Icons.Rounded.ExitToApp) { profileViewModel.logOut() }
        SettingsItem("Theme", Icons.Rounded.Brush) { /*TO DO*/ }
    }
}

@Composable
private fun SettingsItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(10.dp)
            .shadow(4.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp))
            Text(
                text = title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
            )
            Icon(Icons.Default.ArrowForward, contentDescription = "Arrow Icon", modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSurface)
        }
    }
}
