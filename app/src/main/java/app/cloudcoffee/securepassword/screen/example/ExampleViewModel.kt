package app.cloudcoffee.securepassword.screen.example


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class ExampleViewModel: ViewModel() {

    private var theList = listOf<String>()

    private val _idState: MutableState<List<String>> = mutableStateOf(listOf())
    val idState: State<List<String>> = _idState

    init {
        runShuffle()
    }

    fun runShuffle() {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                val uid = UUID.randomUUID().toString()
                val next = theList.toMutableList()
                next.add(uid)
                theList = next
                withContext(Dispatchers.Main) {
                    _idState.value = next
                }
                delay(5000)
            }
        }
    }

    fun doFlow(flow: Flow<String>) {
        GlobalScope.launch {
            flow.collect {

            }
        }

        val someFlow = MutableSharedFlow<String>()

    }
}