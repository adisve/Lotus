package android.app.lotus.observables

import android.app.lotus.app
import android.app.lotus.data.services.DataService
import android.app.lotus.data.services.UserService
import android.app.lotus.data.statemodels.UserInputState
import android.app.lotus.domain.models.constants.UserFields
import android.util.Log
import androidx.lifecycle.MutableLiveData
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
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    HR("HR")
}


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userService: UserService,
    private val dataService: DataService
) : ViewModel() {

    private val _userInputState = MutableStateFlow(UserInputState())
    val userInputState: StateFlow<UserInputState> get() = _userInputState
    private val _user = MutableStateFlow<User?>(null)
    private val user: StateFlow<User?> get() = _user

    /*
    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val phone = MutableLiveData("")
     */

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
        viewModelScope.launch {
            Log.d("ProfileViewModel", "$userFieldsWithId")
            val email = userFieldsWithId["email"] as String
            val password = userFieldsWithId["password"] as String
            userService.createAccount(email, password)
            dataService.upsertUser(email, userFieldsWithId)
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

    fun updateUserInfo(){
        // Update user info in the database - username, email, phone

    }

}
