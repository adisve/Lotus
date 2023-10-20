package android.app.lotus.observables

import android.app.lotus.data.AuthService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class AuthStatus {
    Loading,
    Success,
    Failure
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
) : ViewModel() {

    val status = MutableLiveData(AuthStatus.Loading)
    val username = MutableLiveData("")
    val password = MutableLiveData("")

    init {
        // TODO: Attempt login with securely stored username and password from preferences
        viewModelScope.launch {
            val loginSuccess = authService.attemptLogin("username", "password")
            status.value = if (loginSuccess) AuthStatus.Success else AuthStatus.Failure
        }
    }

    fun updateUsername(newUsername: String) {
        username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }
}

