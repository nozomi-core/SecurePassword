package app.cloudcoffee.securepassword._example

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.suspendCoroutine

@RunWith(JUnit4::class)
class Coroutines {

    @Test
    fun runThis() {
        val future = CompletableFuture<Int>()

        GlobalScope.launch(Dispatchers.Main) {
            val start = System.currentTimeMillis()
            var count = 0

            while((System.currentTimeMillis() - start) < 10000) {
                count++
            }
            future.complete(count)
            Log.v("debug.test", "Counting done")
        }
        GlobalScope.launch(Dispatchers.Main) {
            popThis()
            yield()
        }

        val counter = future.get()
    }

    suspend fun popThis() {
        delay(1000)
        Log.v("debug.test", "PopThis")
    }

}