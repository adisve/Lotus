package android.app.lotus.view.statistics.evaluation

val managerQuestions = listOf(
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
            "\nWould you consider to use this program for relatives and friends?",
    "Question 7/7 \n" +
            "\nDescribe your favorite vacation spot:"
)


val employeeQuestions = managerQuestions.map { question ->
    if ("manager" in question) {
        question.replace("manager", "employee")
    } else question
}


val questionTypes = listOf(
    QuestionType.YES_NO,
    QuestionType.MULTIPLE_CHOICE,
    QuestionType.MULTIPLE_CHOICE,
    QuestionType.MULTIPLE_CHOICE,
    QuestionType.MULTIPLE_CHOICE,
    QuestionType.MULTIPLE_CHOICE,
    QuestionType.TEXT_INPUT
)
