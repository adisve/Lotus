package android.app.lotus

import android.annotation.SuppressLint
import android.app.lotus.data.AuthStatus
import android.app.lotus.observables.MainViewModel
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.general.TopBar
import android.app.lotus.view.navgraph.SetupAuthNavGraph
import android.app.lotus.view.navgraph.SetupMainNavGraph
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus(mainViewModel: MainViewModel, isDarkTheme: Boolean) {
    val authStatus by mainViewModel.authStatus.collectAsState(initial = AuthStatus.Loading)


    when (authStatus) {
        AuthStatus.Success -> {
            val navController = rememberNavController()
            Scaffold (
                topBar = { TopBar(isDarkTheme = isDarkTheme, updateTheme = { isDarkTheme ->
                    mainViewModel.updateTheme(isDarkTheme)
                })},
                bottomBar = { BottomNavigation(navController = navController) }
            ) {
                SetupMainNavGraph(navController)
            }
        }
        AuthStatus.Unauthorized -> {
            val navController = rememberNavController()
            SetupAuthNavGraph(navController, mainViewModel)
        }
        AuthStatus.Loading -> { DotsPulsing() }
    }
}