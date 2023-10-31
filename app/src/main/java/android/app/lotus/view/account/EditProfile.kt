package android.app.lotus.view.account

import android.annotation.SuppressLint
import android.app.lotus.app
import android.app.lotus.observables.MainViewModel
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.auth.InputField
import android.app.lotus.view.buttons.ActionButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import kotlinx.coroutines.flow.map


@Composable
fun EditProfile(
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
                text = "Edit Profile"
            )

            EditProfileForm(profileViewModel)
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition", "FlowOperatorInvokedInComposition")
@Composable
fun EditProfileForm(profileViewModel: ProfileViewModel) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {

        val username by profileViewModel.userInputState.map { it.username }.collectAsState(initial = "")
        val email by profileViewModel.userInputState.map { it.email }.collectAsState(initial = "")
        val phone by profileViewModel.userInputState.map { it.phoneNumber }.collectAsState(initial = "")


        InputField(username, { newText -> profileViewModel.updateEmail(newText)}, false,"Username")
        InputField(email, { newText -> profileViewModel.updateEmail(newText)}, false,"Email")
        InputField(phone, { newText -> profileViewModel.updateEmail(newText)}, false,"Phone")

        Column (
            modifier = Modifier.padding(top = 50.dp)
        ) {
            ActionButton (text = "Save Changes") {
                profileViewModel.updateUserInfo() //TO DO

            }
        }
    }
}

@Composable
private fun InputField(
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


@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    //val navController = remember { NavHostController(android.os.Bundle()) }
    //val profileViewModel = remember { ProfileViewModel() }

    ///EditProfile(NavHostController, ProfileViewModel)
}
