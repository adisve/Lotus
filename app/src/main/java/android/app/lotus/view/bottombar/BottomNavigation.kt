package android.app.lotus.view.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

val items = listOf(
    BottomNavItem.Home,
    BottomNavItem.Statistics,
    BottomNavItem.Profile
)

@Composable
fun BottomNavigation(navController: NavController) {
    val (currentScreen, setCurrentScreen) = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
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
        BottomNavItem.Home -> navController.navigate(screen.route)
        BottomNavItem.Statistics -> navController.navigate(screen.route)
        BottomNavItem.Profile -> navController.navigate(screen.route)
    }
}