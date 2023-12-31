package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.services.DataService
import android.app.lotus.data.services.UserService
import android.app.lotus.data.statemodels.UserInputState
import android.app.lotus.domain.models.constants.UserFields
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.ext.call
import io.realm.kotlin.mongodb.ext.customDataAsBsonDocument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonDocument
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

enum class UserRole(val displayName: String) {
    EMPLOYEE("employee"),
    MANAGER("manager"),
    HR("hr")
}


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userService: UserService,
    private val dataService: DataService
) : ViewModel() {

    private val _userInputState = MutableStateFlow(UserInputState())
    val userInputState: StateFlow<UserInputState> get() = _userInputState
    private val _user = MutableStateFlow<User?>(null)

    init {
        initializeUserListener()
    }

    private fun initializeUserListener() {
        viewModelScope.launch {
            userService.currentUser.collect { user ->
                _user.value = user
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            userService.logOut()
        }
    }

    fun registerNewUser(userFields: Map<String, Any>) {
        val userFieldsWithId = userFields + mapOf(UserFields.id to ObjectId().toHexString())
        val email = userFieldsWithId["email"] as String
        val password = userFieldsWithId["password"] as String
        dataService.upsertUser(email, userFieldsWithId)

        viewModelScope.launch {
            Log.d("ProfileViewModel", "$userFieldsWithId")
            userService.createAccount(email, password)
        }
    }

    fun updateRole(newRole: UserRole) {
        _userInputState.value = _userInputState.value.copy(selectedRole = newRole)
    }

    fun updateUsername(newUsername: String) {
        _userInputState.value = _userInputState.value.copy(username = newUsername)
    }

    fun updatePassword(newPassword: String) {
        _userInputState.value = _userInputState.value.copy(password = newPassword)
    }

    fun updatePhone(newPhone: String) {
        _userInputState.value = _userInputState.value.copy(phoneNumber = newPhone)
    }

    fun updateEmail(newEmail: String) {
        _userInputState.value = _userInputState.value.copy(email = newEmail)
    }

    fun updateCompany(newCompany: String) {
        _userInputState.value = _userInputState.value.copy(company = newCompany)
    }

    fun clearUserInputState() {
        _userInputState.value = UserInputState()
    }

}
