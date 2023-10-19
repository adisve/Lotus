package android.app.lotus.view.navgraph

import Home
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.home.articles.ArticleListScreen
import android.app.lotus.view.home.articles.ArticleView
import android.app.lotus.view.home.videos.VideoView
import android.app.lotus.view.home.videos.VideosScreen
import android.app.lotus.view.profile.ProfileView
import android.app.lotus.view.statistics.StatisticsView
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.home) {
        home(navController)
        stats(navController)
        profile(navController)
        articles(navController)
        videos(navController)
        articleDetail()
        videoDetail()
    }
}

private fun NavGraphBuilder.home(navController: NavHostController) {
    composable(Routes.home) {
        Home(navController)
    }
}

private fun NavGraphBuilder.stats(navController: NavHostController) {
    composable(Routes.stats) {
        StatisticsView(navController)
    }
}

private fun NavGraphBuilder.profile(navController: NavHostController) {
    composable(Routes.profile) {
        ProfileView(navController)
    }
}

private fun NavGraphBuilder.articles(navController: NavHostController) {
    composable(Routes.articles) {
        ArticleListScreen(navController)
    }
}

private fun NavGraphBuilder.articleDetail() {
    composable(Routes.articleDetail) { backStackEntry ->
        val articleId = backStackEntry.arguments?.getString("articleId")
        ArticleView(articleId ?: "")
    }
}

private fun NavGraphBuilder.videos(navController: NavHostController) {
    composable(Routes.videos) {
        VideosScreen(navController)
    }
}

private fun NavGraphBuilder.videoDetail() {
    composable(Routes.videoDetail) { backStackEntry ->
        val videoId = backStackEntry.arguments?.getString("videoId")
        VideoView(videoId = videoId ?: "")
    }
}


