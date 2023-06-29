package app.cloudcoffee.securepassword.security

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cloudcoffee.securepassword.framework.Something
import app.cloudcoffee.securepassword.security.aes.AesDecryptResult
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
        }.getOrNull()

        Assert.assertEquals("hello encryption", result)
    }

    @Test
    fun encryptDecryptResultv2() {
        val result = AesEncryption.encryptUtf8("hello encryption").flatMap { encryptResult ->
            AesEncryption.decrypt(encryptResult.payload).flatMap { decryptResult ->
                finalTransform(decryptResult).flatMap {
                   Something.wrap(1 + 9)
                }
            }
        }.getOrNull()

        Assert.assertEquals(10, result)
    }


    fun finalTransform(res: AesDecryptResult): Something<Int> {
        return Something.wrap(1)
    }
}