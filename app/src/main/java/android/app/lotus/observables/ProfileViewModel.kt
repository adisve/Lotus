package android.app.lotus.observables

import android.app.lotus.data.PrefKey
import android.app.lotus.data.PreferenceService
import android.app.lotus.data.UserService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userService: UserService,
) : ViewModel() {


    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

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

}
