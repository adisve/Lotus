package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.AuthService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.ext.call
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import io.realm.kotlin.mongodb.ext.profileAsBsonDocument
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonDocument
import javax.inject.Inject

enum class AuthStatus {
    Loading,
    Success,
    Unauthorized
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
) : ViewModel() {

    val status = MutableLiveData(AuthStatus.Unauthorized)
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    var user = MutableLiveData<User>(null)

    init {
        /*
        * TODO: Store credentials and load, then attempt auto login
        * login()
        * */
    }

    fun createAccount() {
        status.value = AuthStatus.Loading
        viewModelScope.launch {
            val registrationResult = authService.createAccount(email.value ?: "", password.value ?: "")
            when {
                registrationResult.isSuccess -> login() // Use these new credentials to just sign in
                registrationResult.isFailure -> status.value = AuthStatus.Unauthorized
            }
        }
    }

    fun login() {
        status.value = AuthStatus.Loading
        viewModelScope.launch {
            val result = authService.login(email.value ?: "", password.value ?: "")
            when {
                result.isSuccess -> {
                    val newUser: User = result.getOrThrow()
                    user.value = newUser
                    // Gives info like role, email, etc. that is inserted with custom function
                    // in Atlas that runs each time a new user is created
                    // val customData = newUser.customDataAsBsonDocument()
                    status.value = AuthStatus.Success
                }
                result.isFailure -> status.value = AuthStatus.Unauthorized
            }
        }
    }

    private fun autoLogin(email: String, password: String) {
        // TODO: Implement
    }

    fun updateEmail(newUsername: String) {
        email.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }
}

