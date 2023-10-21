package android.app.lotus.view.home.articles

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.buttons.NavButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(top = 70.dp)


    ) {
        ScrollableList(articleCategories = articleCategories, accountType)
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
                val buttonColors = ButtonDefaults.buttonColors(Color.Blue)

                Button(
                    onClick = {
                        if(accountType == "hr"){
                            //Go to manager version of the article with a button that can change if it is manager or Employee version
                        }else if(accountType == "manager"){
                            //Go to manager version of the article
                        }else if(accountType == "employee"){
                            //Got to employee version of the article
                        }else{
                            //error
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = buttonColors
                ){
                    Text(text = category, fontSize = 16.sp, color = Color.White)
                }
            }

        }
    }
}