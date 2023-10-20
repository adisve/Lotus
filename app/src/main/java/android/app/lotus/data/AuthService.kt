package android.app.lotus.data

import android.app.lotus.app
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import javax.inject.Inject

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

}

