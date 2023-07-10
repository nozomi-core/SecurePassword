package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.security.aes.AesEncryptResult
import javax.crypto.spec.IvParameterSpec

class EncryptedPassword(val ivParameterSpec: IvParameterSpec,
                        val username: AesEncryptResult?,
                        val password: AesEncryptResult?,
                        val email: AesEncryptResult?,
                        val note: AesEncryptResult?) {

    fun decrypt(): UnencryptedPassword {
        val username = username?.decrypt()?.toUtf8String()
        val password = password?.decrypt()?.toUtf8String()
        val email = email?.decrypt()?.toUtf8String()
        val note = note?.decrypt()?.toUtf8String()

        return UnencryptedPassword.of(username, password, email, note)
    }
}