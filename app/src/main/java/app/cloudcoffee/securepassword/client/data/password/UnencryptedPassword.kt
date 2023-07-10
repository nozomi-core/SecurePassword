package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.NULL_STRING
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class Email(val it: String)
data class Username(val it: String)
data class Password(val it: String)
data class Note(val it: String)

class UnencryptedPassword(val username: Username,
                          val password: Password,
                          val email: Email,
                          val note: Note) {

    fun encrypt(): EncryptedPassword {
        val usingIv = AesEncryption.getNextStrongIv()

        val encryptUsername = AesEncryption.encryptUtf8(username.it)
        val encryptPassword = AesEncryption.encryptUtf8(password.it)
        val encryptEmail = AesEncryption.encryptUtf8(email.it)
        val encryptNote = AesEncryption.encryptUtf8(note.it)

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