package android.app.lotus.viewmodel

import android.app.lotus.R
import android.app.lotus.ui.theme.LotusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogInView(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(40.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "Lotus Logo",
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "Welcome to\nLotus Modellen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            lineHeight = 40.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(45.dp))

        LogInForm()
    }
}

@Composable
fun LogInForm() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally,
        verticalArrangement = Arrangement.Center, // Center vertically
    ) {

        InputField("Username")
        InputField("Password")

        Text("Forgot Password?", textAlign = TextAlign.Start, modifier = Modifier.padding(20.dp))

        Button(
            onClick = {
                /*TODO*/
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFF94007C))
        ) {
            Text(
                "Login",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}


@Composable
fun InputField(placeholder: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(placeholder) },
        modifier = Modifier.padding(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LogInViewPreview() {
    LotusTheme {
        LogInView()
    }
}