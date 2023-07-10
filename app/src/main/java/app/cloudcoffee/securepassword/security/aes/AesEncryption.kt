package app.cloudcoffee.securepassword.security.aes

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import java.security.KeyStore
import java.security.SecureRandom
import java.util.Calendar
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AesEncryption {
    private const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"
    private const val KEY_ALIAS = "MyKeyAlias"

    private const val KEY_TYPE = "AES"
    private const val TRANSFORMATION = "AES/CBC/PKCS7PADDING"
    private const val IV_SIZE = 16

    fun generateOrGetKey(): SecretKey? {
        try {
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            keyStore.load(null)

            if(keyStore.containsAlias(KEY_ALIAS)) {
                val keyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry
                return keyEntry.secretKey
            }

            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            val keyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).apply {
                setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                setUserAuthenticationRequired(false)
            }.build()

            keyGenerator.init(keyGenParameterSpec)

            val keyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry
            return keyEntry.secretKey

        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    fun encryptUtf8(stringToEncrypt: String): Maybe<AesEncryptResult> {
        return encrypt(stringToEncrypt.toByteArray(Charsets.UTF_8))
    }

    fun getNextStrongIv(): IvParameterSpec {
        val strongIvBytes = ByteArray(IV_SIZE)
        SecureRandom.getInstanceStrong().nextBytes(strongIvBytes)
        return IvParameterSpec(strongIvBytes)
    }

    fun encrypt(bytesToEncode: ByteArray): Maybe<AesEncryptResult> {
        return try {
            val key = generateOrGetKey()

            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val cipherText = cipher.doFinal(bytesToEncode)

            Maybe.value(AesEncryptResult(cipherText, IvParameterSpec(cipher.iv)))
        } catch (e: Throwable) {
            Maybe.fail(e, FailureCode.ENCRYPTION_ERROR)
        }
    }

    fun decrypt(bytes: ByteArray, ivParameterSpec: IvParameterSpec): Maybe<AesDecryptResult> {
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, generateOrGetKey(), ivParameterSpec)
            val cipherText = cipher.doFinal(bytes)

            Maybe.value(AesDecryptResult(cipherText))
        } catch (e: Throwable) {
            Maybe.fail(e, FailureCode.ENCRYPTION_ERROR)
        }
    }
}





