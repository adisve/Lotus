package android.app.lotus.view.account.hr

import android.app.lotus.data.statemodels.isValid
import android.app.lotus.data.statemodels.toMap
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.view.buttons.ActionButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(profileViewModel: ProfileViewModel, navController: NavController) {

    val userInputState by profileViewModel.userInputState.collectAsState()
    val selectedRole = userInputState.selectedRole
    val username = userInputState.username
    val email = userInputState.email
    val password = userInputState.password
    val phoneNumber = userInputState.phoneNumber
    val company = userInputState.company

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 75.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = "Role: ${selectedRole.displayName.capitalize()}",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(),
                    textStyle = MaterialTheme.typography.bodySmall
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                ) {
                    UserRole.values().forEach { role ->
                        DropdownMenuItem(
                            text = { Text(text = role.displayName.capitalize(), color = MaterialTheme.colorScheme.onPrimary) },
                            onClick = {
                                profileViewModel.updateRole(role)
                                expanded = false
                            }
                        )
                    }
                }
            }

            CustomOutlinedTextField(
                value = username,
                onValueChange = { profileViewModel.updateUsername(it) },
                label = "Username"
            )

            CustomOutlinedTextField(
                value = email,
                onValueChange = { profileViewModel.updateEmail(it) },
                label = "Email"
            )

            CustomOutlinedTextField(
                value = password,
                onValueChange = { profileViewModel.updatePassword(it) },
                label = "Password",
                isPassword = true,
                onDoneAction = { keyboardController?.hide() }
            )

            CustomOutlinedTextField(
                value = company,
                onValueChange = { profileViewModel.updateCompany(it) },
                label = "Company"
            )

            CustomOutlinedTextField(
                value = phoneNumber,
                onValueChange = { profileViewModel.updatePhone(it) },
                label = "Phone Number (Optional)"
            )

            Spacer(modifier = Modifier.padding(top = 25.dp))

            Box(modifier = Modifier.padding(horizontal = 25.dp)) {
                ActionButton(text = "Create account", onClick = {
                    val userFieldsMap = userInputState.toMap()
                    if (userInputState.isValid()) {
                        profileViewModel.registerNewUser(userFieldsMap)
                        profileViewModel.clearUserInputState()
                        navController.navigate(Routes.profile)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("All required fields must be filled")
                        }
                    }
                })
            }
        }
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 75.dp),
            hostState = snackbarHostState
        ) { snackbarData: SnackbarData ->
            CustomSnackBar(
                Icons.Rounded.Keyboard,
                snackbarData.visuals.message,
            )
        }
    }
}

@Composable
fun CustomSnackBar(
    icon: ImageVector,
    message: String
) {
    Snackbar(containerColor = MaterialTheme.colorScheme.background) {
        Row {
            Text(message, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
private fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    onDoneAction: (() -> Unit)? = null
) {
    OutlinedTextField(
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            style = MaterialTheme.typography.bodySmall,
        ) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions.Default.copy(imeAction = ImeAction.Done) else KeyboardOptions.Default,
        keyboardActions = if (isPassword) KeyboardActions(
            onDone = { onDoneAction?.invoke() }
        ) else KeyboardActions.Default
    )
}
