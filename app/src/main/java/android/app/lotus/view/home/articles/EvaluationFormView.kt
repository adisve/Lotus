package android.app.lotus.view.home.articles

import android.app.lotus.R
import android.app.lotus.ui.theme.LotusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EvaluationView() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image( painter = painterResource(R.drawable.lotusmodellen_logo), contentDescription = null,
            modifier = Modifier
                .size(150.dp))
        Spacer(modifier = Modifier.height(90.dp))
        Row (modifier = Modifier
            .align(Alignment.Start)
            .padding(20.dp, 0.dp, 0.dp, 0.dp)) {
            Text(
                text = "Question 1/7",
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left

            )
        }
        Spacer(
            modifier = Modifier.height(20.dp))

        Text(
            text = "Har du som chef läst igenom dokumentationen och handlingsplanen innan du tog del av den webbaserade utbildningen?",
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            color = Color.Black,
            modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = { },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
            modifier = Modifier
                .height(65.dp)
                .width(180.dp)
        ) {
            Text(
                text = "Ja",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {  },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
            modifier = Modifier
                .height(65.dp)
                .width(180.dp)
        ) {

            Text(
                text = "Nej",
                fontSize = 20.sp
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EvaluationPreview() {
    LotusTheme {
        EvaluationView()
    }
}