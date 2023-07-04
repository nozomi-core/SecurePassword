package app.cloudcoffee.securepassword.client.auth

import app.cloudcoffee.securepassword._env.TestEnv
import app.cloudcoffee.securepassword.framework.Maybe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.CompletableFuture

@RunWith(JUnit4::class)
class AuthClientTest {

    val authClient: AuthClient by lazy { FirebaseAuthClient() }

    @Test
    fun testLogin() {
        val future = CompletableFuture<Maybe<UserId>>()
        GlobalScope.launch {
            val result = authClient.loginWithEmail("app.phoenixshell@gmail.com", TestEnv.password)
            future.complete(result)
        }
        val result = future.get()
        Assert.assertTrue(result is Maybe.Value)
    }
}