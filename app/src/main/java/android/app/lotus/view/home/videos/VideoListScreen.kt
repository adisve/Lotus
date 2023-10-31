package android.app.lotus.view.home.videos

import android.app.lotus.domain.models.realm.video
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.VideoListStatus
import android.app.lotus.observables.VideoViewModel
import android.app.lotus.view.general.DotsPulsing
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
            .padding(top = 60.dp, start = 16.dp, end = 16.dp)
            .padding(bottom = 100.dp)
    ) {
        item {
            Text("Educational Videos", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))
        }
        items(video) { video ->
            Box(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 5.dp, vertical = 10.dp)) {
                Card {
                    ListItem(
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            headlineColor = MaterialTheme.colorScheme.onSurface,
                            supportingColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        headlineContent = {
                            Text(
                                video.title,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )},
                        trailingContent = {
                            Icon(
                                Icons.Filled.LiveTv,
                                contentDescription = "Localized description",
                            )
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.homeVideoDetail.replace("{url}", URLEncoder.encode(video.url, "UTF-8")))
                        }
                    )
                }
            }
        }
    }
}