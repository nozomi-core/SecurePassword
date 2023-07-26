package app.cloudcoffee.securepassword.client.clock

import app.cloudcoffee.securepassword._env.testSignal
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(JUnit4::class)
class ClockApiTest: KoinComponent {

    val clockApi: ClockApi by inject()

    @Test
    fun testClockApi() {
        testSignal<Long> { signal ->
            clockApi.pingServerForTime()
            val time = ClientTime.serverUTC
            signal.done(time)
        }
    }
}