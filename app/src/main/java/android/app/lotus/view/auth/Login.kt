package android.app.lotus.view.auth

import android.app.lotus.R
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Login(navController: NavHostController, authViewModel: AuthViewModel) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            painter = painterResource(id = R.drawable.lotus_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(350.dp, 350.dp)
                .align(Alignment.BottomCenter)
        )

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
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                Text(
                    "Before using our app, you need to sign in to your account.",
                    fontSize = 23.sp,
                    textAlign = TextAlign.Start
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                LogInForm(authViewModel, navController)
            }

        }
    }
}

@Composable
fun LogInForm(authViewModel: AuthViewModel, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {

        val username by authViewModel.email.observeAsState("")
        val password by authViewModel.password.observeAsState("")

        InputField(username, { newText -> authViewModel.updateEmail(newText) }, "Email")
        InputField(password, { newText -> authViewModel.updatePassword(newText) }, "Password")


        Column (
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Forgot Password?",
                modifier = Modifier.padding(vertical = 20.dp),
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        }

        Column (
            modifier = Modifier.padding(top = 50.dp)
        ) {
            AuthButton (text = "Login") {
                authViewModel.login()
            }
            AuthButton(text = "Register") {
                navController.navigate(Routes.register)
            }
        }

    }
}

@Composable
fun AuthButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(vertical = 10.dp),
        colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text,
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
        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
        shape = RoundedCornerShape(25.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        )
    )
}
