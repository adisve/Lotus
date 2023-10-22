package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.AuthStatus
import android.app.lotus.data.PrefKey
import android.app.lotus.data.PreferenceService
import android.app.lotus.data.UserService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceService: PreferenceService,
    private val userService: UserService
) : ViewModel() {

    val authStatus: StateFlow<AuthStatus> = userService.authStatus
    private val _isDarkTheme = MutableLiveData<Boolean>()
    val isDarkTheme: LiveData<Boolean> get() = _isDarkTheme
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    init {
        _isDarkTheme.value = preferenceService.getPreference(PrefKey.isDarkTheme, false)
    }

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

    fun updateTheme(isDarkTheme: Boolean) {
        _isDarkTheme.value = isDarkTheme
        preferenceService.setPreference(PrefKey.isDarkTheme, isDarkTheme)
    }
}

