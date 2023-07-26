package app.cloudcoffee.securepassword.client.clock

import android.os.SystemClock

data class ClockResponse(val clientTimeUTC: Long,
                         val serverTimeUTC: Long,
                         val requestDelta: Long) {

    val lastElapsed = SystemClock.elapsedRealtime()

    fun getCurrentUTCElapsed(): Long {
        val elapsedDelta = SystemClock.elapsedRealtime() - lastElapsed
        return serverTimeUTC + elapsedDelta
    }
}