package android.app.lotus.view.navgraph

import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.account.Profile
import android.app.lotus.view.account.Support
import android.app.lotus.view.account.hr.CreateAccount
import android.app.lotus.view.home.Home
import android.app.lotus.view.home.articles.ArticleDetail
import android.app.lotus.view.home.articles.Articles
import android.app.lotus.view.home.videos.Video
import android.app.lotus.view.home.videos.VideoListScreen
import android.app.lotus.view.statistics.Statistics
import android.app.lotus.view.statistics.Stats
import android.app.lotus.view.statistics.evaluation.Evaluation
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.net.URLDecoder

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    articleViewModel: ArticleViewModel,
    ) {
    NavHost(navController = navController, startDestination = Routes.home) {
        home(navController)
        profile(navController, profileViewModel)
        createUserAccount(profileViewModel)
        support(navController)
        stats(navController)
        articles(navController, articleViewModel)
        videos(navController)
        evaluation(navController)
        articleDetail(navController, articleViewModel)
        statistics(navController)
        videoDetail()
    }
}

private fun NavGraphBuilder.createUserAccount(profileViewModel: ProfileViewModel) {
    composable(Routes.createUserAccount) {
        CreateAccount(profileViewModel = profileViewModel)
    }
}

private fun NavGraphBuilder.home(navController: NavHostController) {
    composable(Routes.home) {
        Home(navController)
    }
}

private fun NavGraphBuilder.stats(navController: NavHostController) {
    composable(Routes.stats) {
        Stats(navController)
    }
}

private fun NavGraphBuilder.profile(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
) {
    composable(Routes.profile) {
        Profile(navController, profileViewModel)
    }
}

private fun NavGraphBuilder.support(navController: NavHostController) {
    composable(Routes.support) {
        Support(navController)
    }
}

private fun NavGraphBuilder.articles(navController: NavHostController, articleViewModel: ArticleViewModel) {
    composable(Routes.articles) {
        Articles(navController, articleViewModel)
    }
}

private fun NavGraphBuilder.articleDetail(navController: NavHostController, articleViewModel: ArticleViewModel) {
    composable(Routes.articleDetail) { backStackEntry ->
        val title = backStackEntry.arguments?.getString("title")
        ArticleDetail(navController, articleViewModel, title ?: "")
    }
}

private fun NavGraphBuilder.videos(navController: NavHostController) {
    composable(Routes.videos) {
        VideoListScreen(navController)
    }
}

private fun NavGraphBuilder.statistics(navController: NavHostController) {
    composable(Routes.statistics) {
        Statistics(navController)
    }
}

private fun NavGraphBuilder.videoDetail() {
    composable(Routes.videoDetail) { backStackEntry ->
        val encodedUrl = backStackEntry.arguments?.getString("url")
        val decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8")
        Video(url = decodedUrl ?: "")
    }
}

private fun NavGraphBuilder.evaluation(navController: NavHostController) {
    composable(Routes.evaluation) {
        Evaluation(navController)
    }
}

