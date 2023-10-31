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
        UserFields.role to selectedRole.displayName.lowercase(),
        UserFields.phone to phoneNumber,
        UserFields.company to company
    )
}

fun UserInputState.isValid(): Boolean {
    return this.username.isNotEmpty() &&
            this.password.isNotEmpty() &&
            this.email.isNotEmpty() &&
            this.company.isNotEmpty()
}
