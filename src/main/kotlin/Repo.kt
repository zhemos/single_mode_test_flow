object Repo {

    fun getQuestions(level: Int): List<Question2> {
        val questions = mutableListOf<Question2>()
        repeat(10) {
            val question = Question2(
                name = "Q$level:${it + 1}"
            )
            questions.add(question)
        }
        return questions
    }
}