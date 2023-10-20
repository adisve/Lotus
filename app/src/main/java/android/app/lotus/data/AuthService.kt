package android.app.lotus.data

import javax.inject.Inject

class AuthService @Inject constructor() {
    suspend fun attemptLogin(username: String, password: String): Boolean {
        // TODO: Use Realm app services to log in
        return false // true
    }
}

