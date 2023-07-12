package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe
import app.cloudcoffee.securepassword.framework.NULL_STRING
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import kotlinx.serialization.json.Json

data class Email(val value: String)
data class Username(val value: String)
data class Password(val value: String)
data class Note(val value: String)

class UnencryptedPassword(val username: Username,
                          val password: Password,
                          val email: Email,
                          val note: Note) {

    fun encrypt(): Maybe<EncryptedPassword> {
        val format = PasswordMapper.toFormat(this)
        val jsonString = Json.encodeToString(PasswordFormat.serializer(), format)
        val encryptedPassword = AesEncryption.encryptUtf8(jsonString)

        return encryptedPassword.transform { result ->
            EncryptedPassword(result.ivParameterSpec, result.payload)
        }
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