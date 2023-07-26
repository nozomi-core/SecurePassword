package app.cloudcoffee.securepassword.client.clock

import app.cloudcoffee.securepassword.framework.Maybe

interface ClockApi {
    suspend fun pingServerForTime(): Maybe<ClockResponse>
}