package android.app.lotus.view.analytics.evaluation

val questions = listOf(
    "Have you as a manager read through the documentation and action plan before you took the part in the web-based training?",
    "How would you assess your ability to assimilate to the content of the training?",
    "How would you assess your exchange in education?",
    "How would you assess the education as a whole?",
    "What is your opinion on the issue of prevention of alcohol and drugs at the workplace?",
    "Would you consider suggesting this program to any relatives or friends?",
    "Describe your favorite vacation spot"
)

enum class QuestionType {
    YES_NO, MULTIPLE_CHOICE, TEXT_INPUT
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
