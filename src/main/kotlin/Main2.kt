import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

private val viewModel = SingleModeGameViewModel(Mode.Show)

fun main(args: Array<String>): Unit = runBlocking {
    viewModel.questionUiState.collect {
        println(it)
    }
}