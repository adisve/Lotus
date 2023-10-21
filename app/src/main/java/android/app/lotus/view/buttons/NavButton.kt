package android.app.lotus.view.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NavButton(
    text: String,
    navController: NavHostController,
    route: String
) {
    Button(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        onClick = { navController.navigate(route) }
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
        Icon(
            imageVector = Icons.Rounded.ChevronRight,
            contentDescription = "right pointing arrow"
        )
    }
}
