package android.app.lotus.data

import android.app.lotus.app
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

enum class AuthStatus {
    Loading,
    Success,
    Unauthorized
}

@Singleton
class UserService @Inject constructor() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser
    private val _authStatus = MutableStateFlow<AuthStatus>(AuthStatus.Loading)
    val authStatus: StateFlow<AuthStatus> get() = _authStatus

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                val newUser = fetchUser()
                updateCurrentUser(newUser)
                _authStatus.value = if (newUser != null) AuthStatus.Success else AuthStatus.Unauthorized
                delay(5000)
            }
        }
    }

    private fun updateCurrentUser(user: User?) {
        _currentUser.value = user
    }

    private fun fetchUser(): User? {
        return app.currentUser
    }

    suspend fun createAccount(email: String, password: String): Result<Unit> {
        _authStatus.value = AuthStatus.Loading
        return try {
            app.emailPasswordAuth.registerUser(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        _authStatus.value = AuthStatus.Loading
        return try {
            val user = app.login(Credentials.emailPassword(email, password))
            updateCurrentUser(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logOut() {
        _authStatus.value = AuthStatus.Loading
        app.currentUser?.logOut()
        updateCurrentUser(null)
    }
}


