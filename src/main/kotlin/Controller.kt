import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.LinkedList
import kotlin.random.Random

abstract class Controller {

    private val _questions = mutableMapOf<String, LinkedList<Question>>()
    val questions: Map<String, List<Question>> get() = _questions


    fun getQuestions(): Flow<List<Question>> = flow {

    }

    init {
        println("size all questions in db = ${Database.size}")
        addQuestions(EN)
    }

    private fun addQuestions(categoryTag: String) {
        val queueQuestions = LinkedList<Question>()

        _questions[categoryTag] = queueQuestions
    }

    companion object {
        private const val MAX_ACTIVE_QUESTIONS = 3
    }
}

class ShowController : Controller()

object Database {

    private val questions = mutableListOf<Question>()

    val size get() = questions.size

    fun getQuestion(
        categoryTag: String,
    ): Question? {
        return null
    }

    init {
        repeat(COUNT_OF_CHAPTERS) { chapter ->
            val countOfQuestions = Random.nextInt(MIN_COUNT_OF_QUESTIONS, MAX_COUNT_OF_QUESTIONS)
            repeat(countOfQuestions) {
                val newQuestion = createQuestion(it, EN, chapter, it)
                questions.add(newQuestion)
            }
        }
    }
}

private const val COUNT_OF_CHAPTERS = 10
private const val MIN_COUNT_OF_QUESTIONS = 5
private const val MAX_COUNT_OF_QUESTIONS = 11
const val EN = "eng"
const val SP = "spa"

data class Question(
    val id: Int,
    val categoryTag: String,
    val chapter: Int,
    val index: Int,
    val name: String,
)

fun createQuestion(
    id: Int,
    categoryTag: String,
    chapter: Int,
    index: Int,
) = Question(
    id = id,
    categoryTag = categoryTag,
    chapter = chapter,
    index = index,
    name = "$chapter -> $index"
)