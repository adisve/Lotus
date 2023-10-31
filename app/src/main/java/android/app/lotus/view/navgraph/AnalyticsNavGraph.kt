package android.app.lotus.view.navgraph

import EvaluationForm
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.ArticleViewModel
import android.app.lotus.view.analytics.Analytics
import android.app.lotus.view.analytics.Statistics
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AnalyticsNavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Routes.analytics) {
        analytics(navController)
        statistics(navController)
        evaluation(navController)
    }
}


private fun NavGraphBuilder.statistics(navController: NavHostController) {
    composable(Routes.analyticsStats) {
        Statistics(navController)
    }
}

private fun NavGraphBuilder.analytics(navController: NavHostController) {
    composable(Routes.analytics) {
        Analytics(navController)
    }
}

private fun NavGraphBuilder.evaluation(navController: NavHostController) {
    composable(Routes.analyticsEvaluation) {
        EvaluationForm(navController)
    }
}
