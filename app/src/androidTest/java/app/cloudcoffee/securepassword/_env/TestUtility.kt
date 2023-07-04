package app.cloudcoffee.securepassword._env

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class TestSignal<T>(private val future: CompletableFuture<T>) {

    fun done(result: T) {
        future.complete(result)
    }

    fun exception(e: Throwable) {
        future.completeExceptionally(e)
    }
}

fun <T> testSignal(callback : suspend (signal: TestSignal<T>) -> Unit): T? {
    val future = CompletableFuture<T>()
    val signal = TestSignal(future)
    GlobalScope.launch(Dispatchers.IO) {
        callback(signal)
    }
    val result = future.get()
    return result
}