package android.app.lotus.view.home.articles

import android.app.lotus.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController

@Composable
fun Articles(navController: NavHostController) {
    val articleCategories = listOf(
        "Bakgrund",
        "Förebygg och skapa policy",
        "Fakta om alkoholism",
        "Familjen",
        "Negativa beteenden",
        "Balans och gränsdragning"


    )
    var accountType = "bakgrund"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ) {
        Head()
        ScrollableList(articleCategories = articleCategories, accountType)
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
fun ScrollableList(articleCategories: List<String>, accountType: String){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ){
        items(articleCategories){ category ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val buttonColors = ButtonDefaults.buttonColors(Color.Green)
                Button(
                    onClick = {
                        if(accountType == "IS_HR"){
                            //Go to page to choose between Manger and Employee
                        }else if(accountType == "IS_MANAGER"){
                            //Go to Manager page
                        }else if(accountType == "IS_EMPLOYEE"){
                            //Go to Employee page
                        }else{
                            //ERROR
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                    //colors = buttonColors
                ){
                    Text(text = category, fontSize = 16.sp, color = Color.White)
                }
            }

        }
    }
}