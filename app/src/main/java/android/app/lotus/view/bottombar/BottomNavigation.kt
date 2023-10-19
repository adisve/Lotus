package android.app.lotus.view.bottombar

import android.app.lotus.domain.navigation.Routes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BottomNavigation(navController: NavController, modifier: Modifier = Modifier) {
    val (currentScreen, setCurrentScreen) = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Statistics,
        BottomNavItem.Profile
    )

    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            AddItem(
                screen = item,
                isSelected = currentScreen == item,
                onSelected = {
                    setCurrentScreen(it)
                    navigateToScreen(navController, it)
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    isSelected: Boolean,
    onSelected: (BottomNavItem) -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        alwaysShowLabel = true,
        onClick = { onSelected(screen) },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = screen.title)
        }
    )
}

fun navigateToScreen(navController: NavController, screen: BottomNavItem) {
    when(screen) {
        BottomNavItem.Home -> navController.navigate(Routes.home)
        BottomNavItem.Statistics -> navController.navigate(Routes.stats)
        BottomNavItem.Profile -> navController.navigate(Routes.profile)
    }
}