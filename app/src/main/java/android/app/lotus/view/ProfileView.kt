package android.app.lotus.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ProfileView() {
    Column(modifier = Modifier
        .padding(top = 15.dp)) {
        Text("Profile", textAlign = TextAlign.Center)
        ProfileDescription("Hanna Brown", "hanna.br@gmail.com", "+4689785962")
        SettingsMenu()
    }

}


@Composable
fun ProfileDescription(fullName: String, email: String, phone: String) {
    Column {
        Text(
            fullName,
            fontSize = 40.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            lineHeight = 40.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Column(modifier = Modifier
            .padding(top = 15.dp, bottom = 15.dp)) {
            ProfileDetailItem("Email",  email)
            ProfileDetailItem("Phone", phone)
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
            modifier = Modifier.padding(start = 20.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}


@Composable
fun SettingsMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the box as wide as the screen
            .background(Color.LightGray) // Set the background color of the box
            .padding(15.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 20.sp,
            color = Color.Black
        )
    }

    SettingsItem("Edit Profile")
    SettingsItem("Advice & Support")
    SettingsItem("Log Out")
    SettingsItem("Darkmode")

}


@Composable
fun SettingsItem(title: String) {
    // Icon
    // Text
    // Errow
    Button(
        onClick = {
            // TO DO
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = ButtonDefaults.textButtonColors(contentColor = Color.Yellow)
    ) {

        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Icon",
            modifier = Modifier
                .size(24.dp),
            tint = Color.Black,
        )

        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )


    }

}

