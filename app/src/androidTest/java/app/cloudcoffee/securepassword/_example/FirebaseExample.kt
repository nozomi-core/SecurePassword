package app.cloudcoffee.securepassword._example

import app.cloudcoffee.securepassword._env.testSignal
import app.cloudcoffee.securepassword.client.DatabaseClient
import app.cloudcoffee.securepassword.client.FirebaseDatabaseClient
import app.cloudcoffee.securepassword.client.ObjectCollection
import app.cloudcoffee.securepassword.client.clock.FirebaseClockApi
import app.cloudcoffee.securepassword.client.data.password.FirebasePasswordApi
import app.cloudcoffee.securepassword.client.data.password.UnencryptedPassword
import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.UUID

@JvmInline
value class Email private constructor(val it: String) {

    companion object {
        suspend fun of(email: String?): Maybe<Email> {
            if(email != null) {
                if(!email.contains("@"))
                    return Maybe.fail(FailureCode.NETWORK_EXCEPTION)
                else
                    return Maybe.value(Email(email))
            }
            else
                return Maybe.Companion.fail(FailureCode.SOMETHING_MAPPING)
        }
    }
}
@JvmInline
value class Password(val it: String)
@JvmInline
value class Note(val it: String)

class PasswordModel(
    val email: Email,
    val password: Password,
    val note: Note) {

    companion object {
        suspend fun of(email: String, password: String, note: String): Maybe<PasswordModel> {
            val itEmail = Email.of(email)
            return Maybe.fail(FailureCode.NETWORK_EXCEPTION)
        }
    }
}

@RunWith(JUnit4::class)
class FirebaseExample {

    private val client: DatabaseClient by lazy { FirebaseDatabaseClient() }
    private val PATH = ObjectCollection("app/secure-password/_exam")

    @Test
    fun testFirebase() {
        testSignal { signal ->
            client.insert(PATH, mutableMapOf<String, Any>().apply {
                put("key", "Your name")
            }).onValue {
                signal.done(it.path)
            }.onError {
                signal.exception(Error())
            }
        }
    }

    @Test
    fun testEncrypt() {
        val passwordClient = FirebasePasswordApi()
        val result = testSignal { signal ->
            val password = UnencryptedPassword.of("my_password")
            signal.done(passwordClient.insert(password))
        }
        Assert.assertTrue(result is Maybe.Value)
    }

    @Test
    fun testRead() {
        val passwordClient = FirebasePasswordApi()
        val result = testSignal { signal ->
            val password = passwordClient.getAllPasswords()

            signal.done(password)
        }
        Assert.assertTrue(result is Maybe.Value)
    }

    @Test
    fun testPing() {
        val client = FirebaseClockApi()
        val result = testSignal { signal ->
            client.pingServerForTime()

            signal.done("")
        }
        Assert.assertTrue(result == "")
    }
}