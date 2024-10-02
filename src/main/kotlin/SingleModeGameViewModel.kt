import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SingleModeGameViewModel(private val mode: Mode) {

    private val scope = CoroutineScope(Job())
    private val controller = Controller()

    private val questionUi = MutableSharedFlow<String>()
    val questionUiState: Flow<String> = questionUi.asSharedFlow()

    private val _questionCursor = MutableStateFlow(QuestionCursor(1, 1, 10))
    private val questionCursor: StateFlow<QuestionCursor> = _questionCursor

    init {
        when (mode) {
            Mode.Game -> {}
            Mode.Show -> {}
        }
        scope.launch {
            questionCursor.collect { println(it) }
        }
        showQuestions()
    }

    private fun showQuestions() {
        scope.launch {
            repeat(30) {
                delay(500)
                _questionCursor.value = questionCursor.value.next()
            }
        }
    }
}

data class QuestionCursor(
    val chapter: Int,
    val index: Int,
    val maxQuestions: Int,
) {
    fun next(): QuestionCursor {
        val nextIndex = index + 1
        return if (nextIndex > maxQuestions) {
            copy(chapter = chapter + 1, index = 1)
        } else {
            copy(index = index + 1)
        }
    }
}

sealed class Mode {
    object Game : Mode()
    object Show : Mode()
}

data class QuestionUi(
    val name: String,
)