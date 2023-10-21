package android.app.lotus
import android.annotation.SuppressLint
import android.app.lotus.ui.theme.LotusTheme
import android.app.lotus.view.bottombar.BottomNavigation
import android.app.lotus.view.general.TopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import android.app.lotus.view.navgraph.SetupNavGraph
import androidx.compose.ui.Modifier

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LotusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lotus()
                }
            }
        }
    }
}

