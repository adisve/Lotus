package android.app.lotus.view.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubmitButton(
    onClick: () -> Unit,

    ) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .width(175.dp),
        colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(25.dp),

        ) {
        Text(
            text = "Submit",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}