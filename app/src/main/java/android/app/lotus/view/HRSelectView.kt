package android.app.lotus.view

import android.app.lotus.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HRSelectView(article: String){
    val buttonNameList = listOf(
        "Manager",
        "Employee"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
        //Spacer(modifier = Modifier.weight(1f))
        Head()
        HRSelectButton(buttonNameList = buttonNameList, article)
        Spacer(modifier = Modifier.weight(1f))
        dummySpace()
    }

}
@Composable
private fun Head(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.lotusmodellen_logo),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .padding(bottom = 40.dp)
        )
    }
}

@Composable
fun HRSelectButton(buttonNameList: List<String>, article:String){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
            //.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        items(buttonNameList) {buttonName ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val buttonColors = ButtonDefaults.buttonColors(Color.Green)
                Button(
                    onClick = {
                        //Code
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = buttonColors
                ){
                    Text(text = buttonName, fontSize= 16.sp, color = Color.White)
                }
            }

        }
    }
}

@Composable
fun dummySpace(){
    Text(
        text = "TEXT 123",
        fontSize = 32.sp,
        modifier = Modifier.fillMaxSize()
    )
}