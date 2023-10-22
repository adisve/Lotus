package android.app.lotus.view.statistics.evaluation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Evaluation(navController: NavHostController) {
    val questions = listOf(
        "Question 1/7 \n" +
                "\nHave you as a manager have read through the documentation and action plan before you took the part of the web-based training?",
        "Question 2/7 \n" +
                "\nYour ability to assimilate the content of the training?",
        "Question 3/7 \n" +
                "\nYour exchange of education?",
        "Question 4/7 \n" +
                "\nEducation as a whole?",
        "Question 5/7 \n" +
                "\nWhat is your opinion on the issue of prevention against alcohol and drugs at work?",
        "Question 6/7 \n" +
                "\nould you consider to use this program for relatives and friends?",
        "Question 7/7 \n" +
                "\nDescribe your favorite vacation spot:"
    )

    val questionTypes = listOf(
        QuestionType.YES_NO,
        QuestionType.MULTIPLE_CHOICE,
        QuestionType.MULTIPLE_CHOICE,
        QuestionType.MULTIPLE_CHOICE,
        QuestionType.MULTIPLE_CHOICE,
        QuestionType.MULTIPLE_CHOICE,
        QuestionType.TEXT_INPUT
    )

    var currentQuestion by remember { mutableStateOf(0) }
    val answers = remember { mutableStateListOf<String>() }
    val selectedOption = remember { mutableStateOf("") }
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.padding(50.dp, 0.dp, 50.dp, 0.dp),
        horizontalAlignment = Alignment.Start,

        ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(questions[currentQuestion], fontSize = 23.sp, modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.height(55.dp))

        when (questionTypes[currentQuestion]) {
            QuestionType.YES_NO -> {
                YesNoOption(selectedOption.value) {
                    selectedOption.value = it
                    // Automatically move to the next question when an option is selected
                    answers.add(selectedOption.value)
                    selectedOption.value = ""
                    currentQuestion = nextQuestion(currentQuestion, questions.size)
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
                                answers.add(selectedOption.value)
                                selectedOption.value = ""
                                currentQuestion = nextQuestion(currentQuestion, questions.size)
                            },
                            shape = RoundedCornerShape(25.dp),
                            borderColor = Color(148, 0, 124),
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
                            answers.add(textState.value.text)
                            currentQuestion = nextQuestion(currentQuestion, questions.size)
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color(245, 245, 245))
                        .border(1.dp, Color.Gray)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (currentQuestion == questions.size - 1) {
            Button(
                onClick = {
                    when (questionTypes[currentQuestion]) {
                        QuestionType.YES_NO, QuestionType.MULTIPLE_CHOICE -> {
                            answers.add(selectedOption.value)
                            selectedOption.value = ""
                        }

                        QuestionType.TEXT_INPUT -> {
                            answers.add(textState.value.text)
                        }
                    }
                    //logic when the "Submit" button is clicked.
                },
                modifier = Modifier
                    .height(60.dp)
                    .width(175.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
                shape = RoundedCornerShape(25.dp),
            ) {
                Text(text = "Submit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
// Add a Back button
        val isBackButtonVisible = currentQuestion > 0

                if (isBackButtonVisible) {
                    Button(
                        onClick = {
                            currentQuestion = currentQuestion - 1
                        },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
                        modifier = Modifier
                            .padding(4.dp)
                            .height(55.dp)
                            .width(110.dp)

                    ) {
                        Text(
                            text = "Back",
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                }
            }
    }

@Composable
fun OptionRadioButton(option: String,
                      selectedOption: String,
                      onOptionSelected: () -> Unit,
                      borderColor: Color,
                      shape: Shape,
                      borderStrokeWidth: Dp,
                      size: Dp
) {
    Row(
        horizontalArrangement = Arrangement.Start, // Add spacing between options
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {

        Text(
            text = option,
            fontSize = 19.sp,
            modifier = Modifier
                .clickable { onOptionSelected() }
                .padding(2.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier
            .size(size)
            .border(border = BorderStroke(borderStrokeWidth, borderColor), shape = shape)
            .background(Color.Transparent, shape = shape)
            .clickable { onOptionSelected() })
        if (option == selectedOption) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(borderColor, shape = shape)
            )
        }
    }

}
@Composable
fun YesNoOption(selectedOption: String, onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OptionButton(
            text = "Yes",
            isSelected = selectedOption == "Yes",
            onOptionSelected = { onOptionSelected("Yes") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OptionButton(
            text = "No",
            isSelected = selectedOption == "No",
            onOptionSelected = { onOptionSelected("No") }
        )
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit
) {
    Button(
        onClick = { onOptionSelected() },
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(Color(148, 0, 124)),
        modifier = Modifier
            .padding(4.dp)
            .background(if (isSelected) Color.Gray else Color.White)
            .height(60.dp)
            .width(175.dp)
    ) {
        Text(text = text, color = if (isSelected) Color.White else Color.White,
            fontSize = 20.sp)
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

