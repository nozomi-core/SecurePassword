package app.cloudcoffee.securepassword.security

import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import java.io.IOException
import java.security.InvalidAlgorithmParameterException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.cert.CertificateException
import java.util.Calendar
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

data class EncryptResult(private val payload: ByteArray)

object Secure {
    private const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE = "AndroidKeyStore"
    private const val KEY_ALIAS = "MyKeyAlias"

    private const val KEY_TYPE = "AES"
    private const val TRANSFORMATION = "AES/CBC/PKCS7PADDING"

    private val initialVector = byteArrayOf(0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00)

    fun generateOrGetKey(): SecretKey? {
        try {
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            keyStore.load(null)

            if (!keyStore.containsAlias(KEY_ALIAS)) {
                val keyGenerator = KeyGenerator.getInstance(KEY_TYPE, KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
                keyGenerator.init(256)

                val start = Calendar.getInstance()
                val end = Calendar.getInstance()
                end.add(Calendar.YEAR, 10)

                val myKey = keyGenerator.generateKey()
                val entry = KeyStore.SecretKeyEntry(myKey)
                val procParam = KeyProtection.Builder(KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setKeyValidityStart(start.time)
                    .setKeyValidityEnd(end.time)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build()

                keyStore.setEntry(KEY_ALIAS, entry, procParam)
            }

            val secretKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry
            val secretKey = secretKeyEntry.secretKey

            return secretKey

        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    fun encrypt(bytesToEncode: ByteArray): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, generateOrGetKey(), IvParameterSpec(initialVector))
        val cipherText = cipher.doFinal(bytesToEncode)

        return HexUtil.toHexString(cipherText)
    }

    fun decrypt(hexCipherText: String): ByteArray {
        val bytes = HexUtil.fromHex(hexCipherText)

        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, generateOrGetKey(), IvParameterSpec(initialVector))
        val cipherText = cipher.doFinal(bytes)

        return cipherText
    }
}





