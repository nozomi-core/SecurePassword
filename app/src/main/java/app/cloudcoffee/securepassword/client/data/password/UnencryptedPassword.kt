package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.NULL_STRING
import app.cloudcoffee.securepassword.security.aes.AesEncryption

data class Email(val value: String)
data class Username(val value: String)
data class Password(val value: String)
data class Note(val value: String)

class UnencryptedPassword(val username: Username,
                          val password: Password,
                          val email: Email,
                          val note: Note) {

    fun encrypt(): EncryptedPassword {
        val usingIv = AesEncryption.getNextStrongIv()

        val encryptUsername = AesEncryption.encryptUtf8(username.value)
        val encryptPassword = AesEncryption.encryptUtf8(password.value)
        val encryptEmail = AesEncryption.encryptUtf8(email.value)
        val encryptNote = AesEncryption.encryptUtf8(note.value)

        return EncryptedPassword(
            usingIv,
            encryptUsername.getOrNull(),
            encryptPassword.getOrNull(),
            encryptEmail.getOrNull(),
            encryptNote.getOrNull())
    }

    companion object {

        fun of(username: String? = NULL_STRING,
               password: String? = NULL_STRING,
               email: String? = NULL_STRING,
               note: String? = NULL_STRING): UnencryptedPassword {

            return UnencryptedPassword(
                Username(username ?: NULL_STRING),
                Password(password ?: NULL_STRING),
                Email(email ?: NULL_STRING),
                Note(note ?: NULL_STRING))
        }
    }
}