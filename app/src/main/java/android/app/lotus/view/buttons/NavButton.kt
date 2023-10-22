package android.app.lotus.view.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NavButton(
    text: String,
    suffixIcon: ImageVector? = Icons.Rounded.ArrowForward,
    navController: NavHostController,
    route: String
) {
    Button(
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
        onClick = { navController.navigate(route) }
    ) {
        Row(
            modifier = Modifier.padding((7.5).dp).weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically  // Center-align elements vertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
            if (suffixIcon != null) {
                Icon(
                    imageVector = suffixIcon,
                    contentDescription = "right pointing arrow"
                )
            }
        }
    }
}

