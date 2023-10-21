package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.AuthStatus
import android.app.lotus.data.UserService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {

    val authStatus: StateFlow<AuthStatus> = userService.authStatus
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun createAccount() {
        viewModelScope.launch {
            val registrationResult = userService.createAccount(email.value ?: "", password.value ?: "")
            if (registrationResult.isSuccess) {
                login() // Automatically log in after account creation
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val result = userService.login(email.value ?: "", password.value ?: "")
            if (result.isSuccess) {
                // Gives info like role, email, etc. that is inserted with custom function
                // in Atlas that runs each time a new user is created
                val customData = app.currentUser!!.customDataAsBsonDocument()
                Log.d("AuthViewModel", customData.toString())
            }
        }
    }

    fun updateEmail(newUsername: String) {
        email.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }
}

