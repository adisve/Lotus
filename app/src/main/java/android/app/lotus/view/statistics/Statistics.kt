package android.app.lotus.view.statistics

import android.app.lotus.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Statistics(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        ) {
        Text(
            text = "Welcome HR!",
            fontSize = 19.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "What a good day for reviewing the results.",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(300.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(Color(246, 246, 246)),
        ) {
            Text(
                text = "More",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                fontSize = 18.5.sp,
                modifier = Modifier.padding(23.dp, 2.dp, 0.dp, 0.dp)
            )
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color.White),

            )
        {
            Text(
                text = "Manager Evaluation Forms",
                color = Color.Black,
                textAlign = TextAlign.Left, fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = null,
                Modifier.size(25.dp)
            )
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Text(
                text = "Employee Evaluation Forms",
                color = Color.Black,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = null,
                Modifier.size(25.dp)


            )
        }
    }
}
