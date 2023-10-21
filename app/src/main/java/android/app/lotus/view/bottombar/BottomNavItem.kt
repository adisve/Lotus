package android.app.lotus.view.bottombar

import android.app.lotus.domain.navigation.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.House
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    object Home :
        BottomNavItem(
            "android.app.lotus.view.home.Home",
            Icons.Rounded.House,
            Routes.home
        )

    object Statistics :
        BottomNavItem(
            "Stats",
            Icons.Rounded.BarChart,
            Routes.stats
        )

    object Profile :
        BottomNavItem(
            "Profile",
            Icons.Rounded.Person,
            Routes.profile
        )
}