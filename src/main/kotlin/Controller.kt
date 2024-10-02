import kotlin.math.E
import kotlin.random.Random

class Controller {
    init {
        Database
    }
}

object Database {

    private val questions = mutableListOf<Question>()

    init {
        repeat(COUNT_OF_CHAPTERS) { chapter ->
            val countOfQuestions = Random.nextInt(5, 11)
            repeat(countOfQuestions) {
                val newQuestion = createQuestion(EN, chapter, it)
                questions.add(newQuestion)
            }
        }
    }

//    private val questions = listOf(
//        createQuestion(EN, 1, 1),
//        createQuestion(EN, 1, 2),
//        createQuestion(EN, 1, 3),
//        createQuestion(EN, 1, 4),
//        createQuestion(EN, 1, 5),
//        createQuestion(EN, 1, 1),
//    )
}

private const val COUNT_OF_CHAPTERS = 10
private const val EN = "eng"
private const val SP = "spa"

data class Question(
    val categoryTag: String,
    val chapter: Int,
    val index: Int,
    val name: String,
)

fun createQuestion(
    categoryTag: String,
    chapter: Int,
    index: Int,
) = Question(
    categoryTag = categoryTag,
    chapter = chapter,
    index = index,
    name = "$chapter -> $index"
)