package android.app.lotus.view

import android.app.lotus.types.User
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileView(user: User) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth() // box as wide as the screen
                .background(Color(0xFFA2218C)) // or Light Gray 0xFFF6F6F6
                .padding(15.dp)
        ) {
            Text(
                text = "Profile",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        ProfileDescriptionComponent(user)
        SettingsMenuComponent()
    }
}

@Composable
fun ProfileDescriptionComponent(user: User) {
    Column (modifier = Modifier.padding(20.dp)) {
        Text(
            user.fullName,
            fontSize = 40.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            lineHeight = 40.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Column {
            ProfileDetailItem("Email", user.email)
            ProfileDetailItem("Phone", user.phone)
        }
    }
}

@Composable
fun ProfileDetailItem(title: String, content: String) {
    Column {
        Text(
            "$title: $content",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            //modifier = Modifier.padding(start = 20.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun SettingsMenuComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F6F6))
                .padding(16.dp)
        ) {
            Text(
                text = "Settings",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }

    SettingsItem("Edit Profile", onClick = { /*TO DO*/ })
    SettingsItem("Advice & Support", onClick = { /*TO DO*/ })
    SettingsItem("Log Out", onClick = { /*TO DO*/ })
    SettingsItem("Darkmode", onClick = { /*TO DO*/ })
}


@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
            .then(Modifier.shadow(4.dp)),
        color = Color.White, // Set the background color to white
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 16.dp),
                tint = Color.Gray
            )

            Text(
                text = title,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }
    }
}



