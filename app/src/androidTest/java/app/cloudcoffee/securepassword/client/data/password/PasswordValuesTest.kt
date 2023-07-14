package app.cloudcoffee.securepassword.client.data.password

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PasswordValuesTest {

    @Test
    fun testEmail() {
        val email = Email("email")
        Assert.assertEquals(Email("email"), email)
    }


    fun testNulls(name: String?) {

    }
}