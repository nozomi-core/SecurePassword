package app.cloudcoffee.securepassword._env.api

import app.cloudcoffee.securepassword._env.testSignal
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import app.cloudcoffee.securepassword.client.data.password.UnencryptedPassword
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@RunWith(JUnit4::class)
class PasswordTest: KoinComponent {

    val passwordApi: PasswordApi by inject()

    @Test
    fun insertPassword() {
        testSignal<String> { signal ->
            val password = UnencryptedPassword.of(
                username = "my_username"
            )

            GlobalScope.launch {
                val encrypted = password.encrypt()
                signal.done("")
            }
        }
    }
}