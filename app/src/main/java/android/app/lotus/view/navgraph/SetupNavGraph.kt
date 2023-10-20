package android.app.lotus.view.navgraph

import Home
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.view.home.articles.Articles
import android.app.lotus.view.home.articles.ArticleDetail
import android.app.lotus.view.home.videos.Video
import android.app.lotus.view.home.videos.Videos
import android.app.lotus.view.auth.Auth
import android.app.lotus.view.auth.Profile
import android.app.lotus.view.statistics.Statistics
import android.app.lotus.view.statistics.evaluation.Evaluation
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
        articles(navController)
        videos(navController)
        evaluation(navController)
        auth(navController)
        articleDetail()
        videoDetail()
    }
}

private fun NavGraphBuilder.home(navController: NavHostController) {
    composable(Routes.home) {
        Home(navController)
    }
}

private fun NavGraphBuilder.auth(navController: NavHostController) {
    composable(Routes.auth) {
        Auth(navController)
    }
}

private fun NavGraphBuilder.stats(navController: NavHostController) {
    composable(Routes.stats) {
        Statistics(navController)
    }
}

private fun NavGraphBuilder.articles(navController: NavHostController) {
    composable(Routes.articles) {
        Articles(navController)
    }
}

private fun NavGraphBuilder.articleDetail() {
    composable(Routes.articleDetail) { backStackEntry ->
        val articleId = backStackEntry.arguments?.getString("articleId")
        ArticleDetail(articleId ?: "")
    }
}

private fun NavGraphBuilder.videos(navController: NavHostController) {
    composable(Routes.videos) {
        Videos(navController)
    }
}

private fun NavGraphBuilder.videoDetail() {
    composable(Routes.videoDetail) { backStackEntry ->
        val videoId = backStackEntry.arguments?.getString("videoId")
        Video(videoId = videoId ?: "")
    }
}

private fun NavGraphBuilder.evaluation(navController: NavHostController) {
    composable(Routes.evaluation) {
        Evaluation(navController)
    }
}

