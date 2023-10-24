package android.app.lotus.view.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YesNoOption(selectedOption: String, onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OptionButton(
            text = "Yes",
            isSelected = selectedOption == "Yes",
            onOptionSelected = { onOptionSelected("Yes") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OptionButton(
            text = "No",
            isSelected = selectedOption == "No",
            onOptionSelected = { onOptionSelected("No") }
        )
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit
) {
    Button(
        onClick = { onOptionSelected() },
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .padding(4.dp)
            .height(60.dp)
            .width(175.dp)
    ) {
        Text(
            text = text, color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp
        )
    }
}