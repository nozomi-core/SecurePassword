package app.cloudcoffee.securepassword.helper.b64

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Base64Tests {

    @Test
    fun encodeDriver() {
        val driver64 = Base64Util.encode("this is base64".toByteArray())
        Assert.assertEquals(driver64.encoded, "dGhpcyBpcyBiYXNlNjQ=")
    }

    @Test
    fun decodeDriver() {
        val driver64 = Base64Util.decode(Base64String("dGhpcyBpcyBiYXNlNjQ="))
        Assert.assertEquals(String(driver64), "this is base64")
    }
}