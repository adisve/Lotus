package android.app.lotus.data

import android.app.lotus.app
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor() {
    suspend fun createAccount(email: String, password: String): Result<Unit> {
        return try {
            app.emailPasswordAuth.registerUser(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val user = app.login(Credentials.emailPassword(email, password))
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun checkLoginStatus(): Result<User> {
        val currentUser = app.currentUser
        return if (currentUser != null) {
            Result.success(currentUser)
        } else {
            Result.failure(Exception("No user logged in"))
        }
    }

    suspend fun logOut() {
        app.currentUser?.logOut()
    }


}

