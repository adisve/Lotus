import android.app.lotus.app
import android.app.lotus.domain.models.constants.UserFields
import android.app.lotus.domain.navigation.Routes
import android.app.lotus.observables.EvaluationViewModel
import android.app.lotus.observables.UserRole
import android.app.lotus.utils.getProperty
import android.app.lotus.view.analytics.evaluation.QuestionType
import android.app.lotus.view.analytics.evaluation.questionTypes
import android.app.lotus.view.analytics.evaluation.questions
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EvaluationForm(navController: NavHostController) {
    val evaluationViewModel: EvaluationViewModel = hiltViewModel()
    val role = app.currentUser!!.getProperty(UserFields.role)
    val adjustedQuestions = adjustQuestionsForRole(questions, role)
    val pagerState = rememberPagerState { questions.size }
    val initialAnswers = List(adjustedQuestions.size) { null }
    val answers = remember { mutableStateListOf<Any?>().also { it.addAll(initialAnswers) } }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
            .padding(top = 75.dp, bottom = 100.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            EvaluationQuestionPage(
                page = page,
                question = adjustedQuestions[page],
                answers = answers,
                questionTypes = questionTypes
            )
        }

        if (pagerState.currentPage != 0) {
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = { runBlocking { pagerState.scrollToPage(pagerState.currentPage - 1) } },
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    Spacer(modifier = Modifier.width(7.5.dp))
                    Text("Back", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                if (pagerState.currentPage != pagerState.pageCount - 1) {
                    runBlocking {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    val stringAnswers = answers.map { it?.toString() ?: "" }
                    if (stringAnswers.all { it.isNotEmpty() }) {
                        evaluationViewModel.addEvaluationToUser(stringAnswers)
                        navController.navigate(Routes.analytics)
                    } else {
                        // TODO: Notify the user to answer all questions
                    }
                }
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pagerState.currentPage != pagerState.pageCount - 1) {
                    Text("Next", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(7.5.dp))
                    Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = null)
                } else {
                    Text("Submit", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun EvaluationQuestionPage(page: Int, question: String, answers: MutableList<Any?>, questionTypes: List<QuestionType>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Question ${page + 1} of ${questions.size}", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(35.dp))
        Text(text = question, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(40.dp))

        when (questionTypes[page]) {
            QuestionType.YES_NO -> YesNoQuestion(answers, page)
            QuestionType.MULTIPLE_CHOICE -> MultipleChoiceQuestion(answers, page)
            QuestionType.TEXT_INPUT -> TextInputQuestion(answers, page)
        }
    }
}

@Composable
fun YesNoQuestion(answers: MutableList<Any?>, page: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        listOf("Yes", "No").forEach { option ->
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = answers[page] == option,
                    onClick = { answers[page] = option }
                )
                Text(text = option, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun MultipleChoiceQuestion(answers: MutableList<Any?>, page: Int) {
    val multipleChoiceStrings = mapOf(1 to "Bad", 2 to "Not so good", 3 to "Moderate", 4 to "Good", 5 to "Very good")
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        (1..5).forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        answers[page] = option
                    }
            ) {
                RadioButton(
                    selected = answers[page] == option,
                    onClick = { answers[page] = option }
                )
                Text(text = "$option - ${multipleChoiceStrings[option]}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun TextInputQuestion(answers: MutableList<Any?>, page: Int) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            answers[page] = it.text
        }
    )
}

fun adjustQuestionsForRole(questions: List<String>, role: String): List<String> {
    if (role == UserRole.EMPLOYEE.displayName) {
        return questions.map { it.replace("manager", "employee") }
    }
    return questions
}
