package android.app.lotus.view.home.videos

import android.app.lotus.domain.models.realm.video
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.VideoListStatus
import android.app.lotus.observables.VideoViewModel
import android.app.lotus.view.buttons.NavButton
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import java.net.URLEncoder

@Composable
fun VideoListScreen(navController: NavHostController) {
    val videoViewModel: VideoViewModel = hiltViewModel()
    val status = videoViewModel.status.observeAsState(initial = VideoListStatus.Loading)

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (status.value) {
            VideoListStatus.Loading -> {
                DotsPulsing()
            }

            VideoListStatus.Empty -> {
                Text("Nothing here yet", style = MaterialTheme.typography.headlineLarge)
            }

            VideoListStatus.Populated -> {
                ScrollableVideoList(navController, videoViewModel.videoList)
            }
        }
    }
}

@Composable
fun ScrollableVideoList(navController: NavHostController, video: List<video>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(video) { video ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavButton(
                    text = video.title,
                    navController = navController,
                    route = Routes.videoDetail.replace("{url}", URLEncoder.encode(video.url, "UTF-8")),
                    suffixIcon = null
                )
            }

        }
    }
}