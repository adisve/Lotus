package android.app.lotus.view.auth

import android.app.lotus.observables.AuthStatus
import android.app.lotus.observables.AuthViewModel
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Auth(navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authStatus by authViewModel.status.observeAsState(AuthStatus.Loading)

    when (authStatus) {
        AuthStatus.Loading -> {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DotsPulsing()
            }
        }
        AuthStatus.Success -> {
            Profile(authViewModel, navController)
        }
        AuthStatus.Unauthorized -> {
            Login(authViewModel, navController)
        }
    }
}
