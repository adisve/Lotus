package android.app.lotus.view.auth

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.MainViewModel
import android.app.lotus.view.buttons.ActionButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Login(mainViewModel: MainViewModel) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 75.dp)
            ) {
                Text(
                    "Welcome to Lotus",
                    modifier = Modifier.padding(bottom = 30.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Start
                )
                Text(
                    "Before using our app, you need to sign in to your account.",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                LogInForm(mainViewModel)
            }

        }
    }
}

@Composable
fun LogInForm(mainViewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {

        val username by mainViewModel.email.observeAsState("")
        val password by mainViewModel.password.observeAsState("")

        InputField(username, { newText -> mainViewModel.updateEmail(newText) }, false,"Email")
        InputField(password, { newText -> mainViewModel.updatePassword(newText) }, true,"Password")


        Column (
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Forgot Password?",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 20.dp),
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        }

        Column (
            modifier = Modifier.padding(top = 50.dp)
        ) {
            ActionButton (text = "Login") {
                mainViewModel.login()
            }
        }

    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        label = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )
        },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = RoundedCornerShape(15.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        )
    )
}
