package android.app.lotus.view.auth

import android.app.lotus.observables.AuthViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Login(authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(horizontal = 25.dp)
        ) {
            LogInForm(authViewModel)
        }

    }
}

@Composable
fun LogInForm(authViewModel: AuthViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val username by authViewModel.email.observeAsState("")
        val password by authViewModel.password.observeAsState("")

        InputField(username, { newText -> authViewModel.updateEmail(newText) }, "Email")
        InputField(password, { newText -> authViewModel.updatePassword(newText) }, "Password")

        Text("Forgot Password?", textAlign = TextAlign.Start, modifier = Modifier.padding(20.dp))

        LoginButton {
            authViewModel.login()
        }

    }
}

@Composable
fun LoginButton(login: () -> Unit) {
    Button(
        onClick = login,
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Login",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        label = {
            Text(text = placeholder, color = MaterialTheme.colorScheme.primary)
        },
        shape = RoundedCornerShape(25.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        )
    )
}
