package app.cloudcoffee.securepassword.client.clock

import java.time.Clock

object ClientTime {

    val serverUTC: Long get() {
        val clockResponse = clockResponse

        if(clockResponse == null)
            throw IllegalStateException("Server UTC is not ready, please set initial sync time before accessing")
        else {
            /*
            * Calculates the difference between now and last time server time was requested to get the new time based on server clock
            * */
            return clockResponse.getCurrentUTCElapsed()
        }
    }

    private var clockResponse: ClockResponse? = null

    fun setServerClockResponse(clockResponse: ClockResponse) {
        this.clockResponse = clockResponse
    }
}