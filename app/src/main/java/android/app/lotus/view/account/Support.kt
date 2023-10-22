package android.app.lotus.view.account

import android.app.lotus.observables.SupportViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun Support(navController: NavHostController){
    val viewModel: SupportViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = 70.dp),
    ){
        Text("Message:", modifier = Modifier.padding(bottom = 16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(4.dp)
        ) {
            BasicTextField(
                value = viewModel.message,
                onValueChange = {newMessage ->
                    viewModel.message = newMessage
                },
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = viewModel.message)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Button(onClick = {
            //Code to send message to client
            viewModel.message = ""
        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Send Message", fontSize = 16.sp, color = Color.White)
        }
    }
}