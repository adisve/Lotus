package android.app.lotus.view.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionRadioButton(option: String,
                      selectedOption: String,
                      onOptionSelected: () -> Unit,
                      borderColor: Color,
                      shape: Shape,
                      borderStrokeWidth: Dp,
                      size: Dp
) {
    Row(
        horizontalArrangement = Arrangement.Start, // Add spacing between options
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {

        Text(
            text = option,
            fontSize = 19.sp,
            modifier = Modifier
                .clickable { onOptionSelected() }
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier
            .size(size)
            .border(border = BorderStroke(borderStrokeWidth, borderColor), shape = shape)
            .background(Color.Transparent, shape = shape)
            .clickable { onOptionSelected() })
        if (option == selectedOption) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(borderColor, shape = shape)
            )
        }
    }

}