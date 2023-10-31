package android.app.lotus.view.bottombar

import android.app.lotus.domain.navigation.Routes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    HOME(Routes.home, Icons.Rounded.Home, "Home"),
    ANALYTICS(Routes.analytics, Icons.Rounded.BarChart, "Analytics"),
    ACCOUNT(Routes.home, Icons.Rounded.Person, "Account"),
}

@Composable
fun BottomNavigation(
    currentNavGraph: MutableState<BottomNavItem>
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        BottomNavItem.values().forEach { item ->
            AddItem(
                navItem = item,
                isSelected = currentNavGraph.value == item,
                onSelected = {
                    currentNavGraph.value = it
                }
            )
        }
    }
}



@Composable
fun RowScope.AddItem(
    navItem: BottomNavItem,
    isSelected: Boolean,
    onSelected: (BottomNavItem) -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        alwaysShowLabel = true,
        onClick = { onSelected(navItem) },
        icon = {
            Icon(imageVector = navItem.icon, contentDescription = navItem.title)
        }
    )
}
