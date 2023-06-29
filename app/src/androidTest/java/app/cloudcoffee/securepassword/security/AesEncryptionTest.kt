package app.cloudcoffee.securepassword.security

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AesEncryptionTest {


    @Test
    fun encryptDecryptResult() {
        val result = AesEncryption.encryptUtf8("hello encryption").transform  { result ->
            AesEncryption.decrypt(result.payload).transform {
                it.toUtf8String()
            }.getOrNull()
        }

        Assert.assertEquals("hello encryption", result)
    }
}