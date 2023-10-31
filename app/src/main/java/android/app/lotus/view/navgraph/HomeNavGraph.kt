package android.app.lotus.view.navgraph

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.home.Home
import android.app.lotus.view.home.articles.ArticleDetail
import android.app.lotus.view.home.articles.Articles
import android.app.lotus.view.home.videos.Video
import android.app.lotus.view.home.videos.VideoListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.net.URLDecoder

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    articleViewModel: ArticleViewModel
) {
    NavHost(navController = navController, startDestination = Routes.home) {
        home(navController)
        articles(navController, articleViewModel)
        articleDetail(navController, articleViewModel)
        videos(navController)
        videoDetail()
    }
}

private fun NavGraphBuilder.home(navController: NavHostController) {
    composable(Routes.home) {
        Home(navController)
    }
}

private fun NavGraphBuilder.articles(navController: NavHostController, articleViewModel: ArticleViewModel) {
    composable(Routes.homeArticles) {
        Articles(navController, articleViewModel)
    }
}

private fun NavGraphBuilder.articleDetail(navController: NavHostController, articleViewModel: ArticleViewModel) {
    composable(Routes.homeArticleDetail) { backStackEntry ->
        val title = backStackEntry.arguments?.getString("title")
        ArticleDetail(navController, articleViewModel, title ?: "")
    }
}

private fun NavGraphBuilder.videos(navController: NavHostController) {
    composable(Routes.homeVideos) {
        VideoListScreen(navController)
    }
}

private fun NavGraphBuilder.videoDetail() {
    composable(Routes.homeVideoDetail) { backStackEntry ->
        val encodedUrl = backStackEntry.arguments?.getString("url")
        val decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8")
        Video(url = decodedUrl ?: "")
    }
}