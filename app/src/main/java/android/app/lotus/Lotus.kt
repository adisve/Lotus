package android.app.lotus

import android.annotation.SuppressLint
import android.app.lotus.data.services.AuthStatus
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.observables.MainViewModel
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.auth.Login
import android.app.lotus.view.bottombar.BottomNavItem
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.DotsPulsing
import android.app.lotus.view.general.TopBar
import android.app.lotus.view.navgraph.AccountNavGraph
import android.app.lotus.view.navgraph.AnalyticsNavGraph
import android.app.lotus.view.navgraph.HomeNavGraph
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Lotus(mainViewModel: MainViewModel, isDarkTheme: Boolean) {
    val authStatus by mainViewModel.authStatus.collectAsState(initial = AuthStatus.Loading)

    val homeNavController = rememberNavController()
    val profileNavController = rememberNavController()
    val analyticsNavController = rememberNavController()
    val currentNavGraph = remember { mutableStateOf(BottomNavItem.HOME) }

    when (authStatus) {
        AuthStatus.Success -> {
            Scaffold (
                topBar = { TopBar(isDarkTheme = isDarkTheme, updateTheme = { isDarkTheme ->
                    mainViewModel.updateTheme(isDarkTheme)
                })},
                bottomBar = { BottomBar(
                    homeNavController,
                    profileNavController,
                    analyticsNavController,
                    currentNavGraph,
                ) }

            ) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val articleViewModel: ArticleViewModel = hiltViewModel()

                when (currentNavGraph.value) {
                    BottomNavItem.HOME -> {
                        HomeNavGraph(navController = homeNavController, articleViewModel = articleViewModel)
                    }
                    BottomNavItem.ACCOUNT -> {
                        AccountNavGraph(navController = profileNavController, profileViewModel = profileViewModel)
                    }
                    BottomNavItem.ANALYTICS -> {
                        AnalyticsNavGraph(navController = analyticsNavController)
                    }
                }
            }
        }
        AuthStatus.Unauthorized -> {
            Login(mainViewModel = mainViewModel)
        }
        AuthStatus.Loading -> { DotsPulsing() }
    }
}

@Composable
fun BottomBar(
    homeNavController: NavHostController,
    profileNavController: NavHostController,
    analyticsNavController: NavHostController,
    currentNavGraph: MutableState<BottomNavItem>,
) {
    BottomNavigation(currentNavGraph) { selectedGraph ->
        if (currentNavGraph.value == selectedGraph) {
            when (selectedGraph) {
                BottomNavItem.HOME -> homeNavController.graph.startDestinationRoute?.let {
                    if (homeNavController.currentDestination?.route != it) {
                        homeNavController.popBackStack(it, false)
                    }
                }
                BottomNavItem.ACCOUNT -> profileNavController.graph.startDestinationRoute?.let {
                    if (profileNavController.currentDestination?.route != it) {
                        profileNavController.popBackStack(it, false)
                    }
                }
                BottomNavItem.ANALYTICS -> analyticsNavController.graph.startDestinationRoute?.let {
                    if (analyticsNavController.currentDestination?.route != it) {
                        analyticsNavController.popBackStack(it, false)
                    }
                }
            }

        } else {
            currentNavGraph.value = selectedGraph
        }
    }
}