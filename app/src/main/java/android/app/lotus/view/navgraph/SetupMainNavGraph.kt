package android.app.lotus.view.navgraph

import android.app.lotus.view.account.hr.CreateAccount
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.HomeViewModel
import android.app.lotus.observables.ProfileViewModel
import android.app.lotus.view.account.Profile
import android.app.lotus.view.account.Support
import android.app.lotus.view.home.Home
import android.app.lotus.view.home.articles.ArticleDetail
import android.app.lotus.view.home.articles.Articles
import android.app.lotus.view.home.videos.Video
import android.app.lotus.view.home.videos.Videos
import android.app.lotus.view.statistics.Statistics
import android.app.lotus.view.statistics.evaluation.Evaluation
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupMainNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel,
    ) {
    NavHost(navController = navController, startDestination = Routes.home) {
        home(navController, homeViewModel)
        profile(navController, profileViewModel)
        createUserAccount(profileViewModel)
        support(navController)
        stats(navController)
        articles(navController)
        videos(navController)
        evaluation(navController)
        articleDetail(navController)
        videoDetail()
    }
}

private fun NavGraphBuilder.createUserAccount(profileViewModel: ProfileViewModel) {
    composable(Routes.createUserAccount) {
        CreateAccount(profileViewModel = profileViewModel)
    }
}

private fun NavGraphBuilder.home(navController: NavHostController, homeViewModel: HomeViewModel) {
    composable(Routes.home) {
        Home(navController, homeViewModel)
    }
}

private fun NavGraphBuilder.stats(navController: NavHostController) {
    composable(Routes.stats) {
        Statistics(navController)
    }
}

private fun NavGraphBuilder.profile(navController: NavHostController, profileViewModel: ProfileViewModel) {
    composable(Routes.profile) {
        Profile(navController, profileViewModel)
    }
}

private fun NavGraphBuilder.support(navController: NavHostController) {
    composable(Routes.support) {
        Support(navController)
    }
}

private fun NavGraphBuilder.articles(navController: NavHostController) {
    composable(Routes.articles) {
        Articles(navController)
    }
}

private fun NavGraphBuilder.articleDetail(navController: NavHostController) {
    composable(Routes.articleDetail) { backStackEntry ->
        val articleName = backStackEntry.arguments?.getString("articleName")
        ArticleDetail(navController, articleName ?: "")
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

