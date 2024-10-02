import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/***
 * level => -
 * round => level?,
 * index => level
 *
 * list questions => level
 * one question => index, list questions
 */

val currentLevel = flow<Int> {
    emit(1)
    delay(1000)
    emit(2)
}
val currentRound = flow {
    emit(1)
    delay(1100)
    emit(1)
}
val currentIndexOfQuestions = flow {
    emit(4)
    delay(2000)
    emit(1)
}

fun main(args: Array<String>): Unit = runBlocking {
    val questions = getQuestions(currentLevel)
    getQuestion(
        questionsFlow = questions,
        indexFlow = currentIndexOfQuestions,
    ).collect {
        println(it)
    }
//    combine(currentLevel, currentRound, currentIndexOfQuestions) { level, round, index ->
//        "$level $round $index"
//    }.onEach { println(it) }.collect()
}

fun getQuestion(
    questionsFlow: Flow<List<Question2>>,
    indexFlow: Flow<Int>,
): Flow<Question2> = flow {
    combine(questionsFlow, indexFlow) { questions, index ->
        emit(questions[index])
    }.collect()
}

fun getQuestions(
    currentLevel: Flow<Int>,
): Flow<List<Question2>> = flow {
    currentLevel.collect { level ->
        emit(Repo.getQuestions(level))
    }
}

data class Question2(
    val name: String,
)