package android.app.lotus.view.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Support(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp)
                .padding(horizontal = 25.dp)
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                text = "Need advice or support?"
            )
            Text(
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 30.sp,
                text = "Contact us at:"
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 30.sp,
                text = """
                    AB Lotusmodellen
                    Gesällvägen 2
                    294 77 Sölvesborg
                    0706-25 57 50
                    p-o@lotusmodellen.se
                """.trimIndent()
            )
        }
    }
}