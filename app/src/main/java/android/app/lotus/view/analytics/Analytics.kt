package android.app.lotus.view.analytics

import android.app.lotus.R
import android.app.lotus.app
import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.UserRole
import android.app.lotus.utils.getProperty
import android.app.lotus.view.buttons.NavButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.realm.kotlin.mongodb.User

@Composable
fun Analytics(navController: NavHostController) {
    val user = app.currentUser!!
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (user.getProperty(UserFields.role)) {
                UserRole.HR.displayName -> {
                    HrIntro(navController)
                }
                else -> {
                    EvaluationIntro(navController, user)
                }
            }
        }
    }
}

@Composable
fun HrIntro(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro_hr),
            contentDescription = "Evaluation Vector",
            modifier = Modifier.width(300.dp)
        )
        Column {
            NavButton(
                text = "Evaluation Statistics",
                navController = navController,
                route = Routes.analyticsStats
            )
            NavButton(
                text = "Manager evaluation",
                navController = navController,
                route = Routes.analyticsEvaluation
            )
            NavButton(
                text = "Employee evaluation",
                navController = navController,
                route = Routes.analyticsEvaluation
            )
        }
    }
}

@Composable
fun EvaluationIntro(navController: NavHostController, user: User) {
    val imageResource = when (user.getProperty(UserFields.role)) {
        UserRole.MANAGER.displayName -> R.drawable.intro_employee
        UserRole.EMPLOYEE.displayName -> R.drawable.intro_manager
        else -> null
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 25.dp)
        ) {
            Text(
                "Complete your ${user.getProperty(UserFields.role)} evaluation form",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 50.dp)
            )
            Image(
                painter = painterResource(id = imageResource ?: R.drawable.intro_employee),
                contentDescription = "Evaluation Vector",
                modifier = Modifier.width(300.dp)
            )
        }
        NavButton(
            text = "Start evaluation",
            navController = navController,
            route = Routes.analyticsEvaluation
        )
    }
}