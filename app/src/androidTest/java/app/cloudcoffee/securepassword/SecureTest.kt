package app.cloudcoffee.securepassword

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cloudcoffee.securepassword.security.HexUtil
import app.cloudcoffee.securepassword.security.Secure
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.spec.IvParameterSpec

@RunWith(AndroidJUnit4::class)
class SecureTest {


    @Test
    fun get_key_returns_same_key() {
        val popcorn = Secure.encrypt("hello".toByteArray(Charsets.UTF_8))
        Assert.assertEquals("hello", Secure.decrypt(popcorn))

    }

    @Test
    fun testEncryptDecrypt() {

    }
}