package android.app.lotus.view.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButtonComposable(onBackClick: () -> Unit) {
    Button(
        onClick = {
            onBackClick()
        },
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(4.dp)
            .height(55.dp)
            .width(110.dp)
    ) {
        Text(
            text = "Back",
            fontSize = 20.sp,
            color =  MaterialTheme.colorScheme.onPrimary,
        )
    }
}