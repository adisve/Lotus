package android.app.lotus.view.statistics.evaluation

import android.app.lotus.observables.StatisticsViewModel
import android.app.lotus.view.buttons.BackButtonComposable
import android.app.lotus.view.buttons.OptionRadioButton
import android.app.lotus.view.buttons.SubmitButton
import android.app.lotus.view.buttons.YesNoOption
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun Evaluation(navController: NavHostController) {

    val statisticsViewModel: StatisticsViewModel = viewModel()
    val currentQuestion = statisticsViewModel.currentQuestion.value


    val answers =
        remember { mutableStateMapOf<Int, String>() } // Use a mutableStateMapOf instead of mutableStateListOf
    val selectedOption = remember { mutableStateOf("") }
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(horizontal = 25.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                employeeQuestions[statisticsViewModel.currentQuestion.value ?: 0],
                fontSize = 23.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(55.dp))

            when (questionTypes[statisticsViewModel.currentQuestion.value ?: 0]) {
                QuestionType.YES_NO -> {
                    YesNoOption(selectedOption.value) {
                        selectedOption.value = it
                        // Automatically move to the next question when an option is selected
                        answers[statisticsViewModel.currentQuestion.value ?: 0] =
                            selectedOption.value
                        selectedOption.value = ""
                        statisticsViewModel.currentQuestion.value = nextQuestion(
                            statisticsViewModel.currentQuestion.value ?: 0,
                            managerQuestions.size
                        )
                    }
                }

                QuestionType.MULTIPLE_CHOICE -> {
                    val options = listOf("1", "2", "3", "4", "5")
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        options.forEach { option ->
                            OptionRadioButton(
                                option = option,
                                selectedOption = selectedOption.value,
                                onOptionSelected = {
                                    selectedOption.value = option
                                    // Automatically move to the next question when an option is selected
                                    answers[statisticsViewModel.currentQuestion.value ?: 0] =
                                        selectedOption.value
                                    selectedOption.value = ""
                                    statisticsViewModel.currentQuestion.value = nextQuestion(
                                        statisticsViewModel.currentQuestion.value ?: 0,
                                        managerQuestions.size
                                    )
                                },
                                shape = RoundedCornerShape(25.dp),
                                borderColor = MaterialTheme.colorScheme.primary,
                                borderStrokeWidth = 6.dp,
                                size = 25.dp
                            )
                        }
                    }
                }

                QuestionType.TEXT_INPUT -> {
                    BasicTextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 18.sp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                answers[statisticsViewModel.currentQuestion.value ?: 0] =
                                    textState.value.text
                                statisticsViewModel.currentQuestion.value = nextQuestion(
                                    statisticsViewModel.currentQuestion.value ?: 0,
                                    managerQuestions.size
                                )
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color(245, 245, 245))
                            .border(1.dp, Color.Gray)
                            .padding(20.dp, 0.dp, 0.dp, 0.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (currentQuestion == managerQuestions.size - 1) {
                SubmitButton(
                    onClick = {
                        when (questionTypes[currentQuestion]) {
                            QuestionType.YES_NO, QuestionType.MULTIPLE_CHOICE -> {
                                answers[currentQuestion] = selectedOption.value
                                selectedOption.value = ""
                            }

                            QuestionType.TEXT_INPUT -> {
                                answers[currentQuestion] = textState.value.text
                            }
                        }

                        statisticsViewModel.currentQuestion.value =
                            nextQuestion(currentQuestion, managerQuestions.size)

                        // Print out the answers
                        println("Answers:")
                        for ((questionIndex, answer) in answers) {
                            println("Question $questionIndex: $answer")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            val isBackButtonVisible = (statisticsViewModel.currentQuestion.value ?: 0) > 0
            if (isBackButtonVisible) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    BackButtonComposable {
                        // Remove the answer for the current question when going back
                        answers.remove(currentQuestion)
                        statisticsViewModel.currentQuestion.value =
                            statisticsViewModel.currentQuestion.value?.minus(
                                1
                            )
                    }
                }
            }
        }
    }
}

fun nextQuestion(currentQuestion: Int, totalQuestions: Int): Int {
    return if (currentQuestion < totalQuestions - 1) {
        currentQuestion + 1
    } else {
        currentQuestion
    }
}

enum class QuestionType {
    YES_NO, MULTIPLE_CHOICE, TEXT_INPUT
}


