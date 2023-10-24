package android.app.lotus.data.statemodels

import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.observables.UserRole
import androidx.compose.ui.text.toLowerCase

data class UserInputState(
    val selectedRole: UserRole = UserRole.EMPLOYEE,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val company: String = ""
)

fun UserInputState.toMap(): Map<String, Any> {
    return mapOf(
        UserFields.username to username,
        UserFields.email to email,
        UserFields.password to password,
        UserFields.role to selectedRole.displayName,
        UserFields.phone to phoneNumber,
        UserFields.company to company
    )
}