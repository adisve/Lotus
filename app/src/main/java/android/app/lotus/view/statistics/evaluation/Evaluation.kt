package android.app.lotus.view.statistics.evaluation

import android.app.lotus.observables.EvaluationViewModel
import android.app.lotus.view.buttons.BackButtonComposable
import android.app.lotus.view.buttons.OptionRadioButton
import android.app.lotus.view.buttons.SubmitButton
import android.app.lotus.view.buttons.YesNoOption
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Evaluation(navController: NavHostController) {

    val evaluationViewModel: EvaluationViewModel = hiltViewModel()

    val currentQuestion = remember { mutableStateOf(0) }
    val answers =
        remember { mutableStateMapOf<Int, String>() } // Use a mutableStateMapOf instead of mutableStateListOf
    val selectedOption = remember { mutableStateOf("") }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Log.v("Evaluation", "currentQuestion.value: $currentQuestion.value")
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
                employeeQuestions[currentQuestion.value],
                fontSize = 23.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(55.dp))

            when (questionTypes[currentQuestion.value]) {
                QuestionType.YES_NO -> {
                    YesNoOption(selectedOption.value) {
                        selectedOption.value = it
                        // Automatically move to the next question when an option is selected
                        answers[currentQuestion.value] =
                            selectedOption.value
                        selectedOption.value = ""
                        currentQuestion.value += if (currentQuestion.value < managerQuestions.size - 1) 1 else 0
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
                                    answers[currentQuestion.value] =
                                        selectedOption.value
                                    selectedOption.value = ""
                                    currentQuestion.value +=
                                        if (currentQuestion.value < managerQuestions.size - 1) 1 else 0

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
                                answers[currentQuestion.value] =
                                    textState.value.text
                                currentQuestion.value +=
                                    if (currentQuestion.value < managerQuestions.size - 1) 1 else 0
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

            if (currentQuestion.value == managerQuestions.size - 1) {
                SubmitButton(
                    onClick = {
                        when (questionTypes[currentQuestion.value]) {
                            QuestionType.YES_NO, QuestionType.MULTIPLE_CHOICE -> {
                                answers[currentQuestion.value] = selectedOption.value
                                selectedOption.value = ""
                            }

                            QuestionType.TEXT_INPUT -> {
                                answers[currentQuestion.value] = textState.value.text
                            }
                        }

                        currentQuestion.value = 0

                        evaluationViewModel.addEvaluationToUser(answers.toMap().values.toList())
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (currentQuestion.value > 0) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    BackButtonComposable(
                        onBackClick = { ->
                            currentQuestion.value--
                        }
                    )
                }
            }
        }
    }
}

enum class QuestionType {
    YES_NO, MULTIPLE_CHOICE, TEXT_INPUT
}


