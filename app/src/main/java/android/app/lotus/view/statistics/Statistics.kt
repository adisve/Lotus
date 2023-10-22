package android.app.lotus.view.statistics

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.buttons.NavButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Statistics(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            // TODO: Implement graphs, pressable preview that navigates sub route
        }

        Column (
            modifier = Modifier.padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavButton(
                text = "Manager evaluation",
                navController = navController,
                route = Routes.evaluation
            )
            NavButton(
                text = "Employee evaluation",
                navController = navController,
                route = Routes.evaluation
            )
        }
    }
}
