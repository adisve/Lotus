package android.app.lotus.view.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.House
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector
) {
    object Home :
        BottomNavItem(
            "Home",
            Icons.Rounded.House
        )

    object Statistics :
        BottomNavItem(
            "Stats",
            Icons.Rounded.BarChart
        )

    object Profile :
        BottomNavItem(
            "Profile",
            Icons.Rounded.Person
        )
}