package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.extensions.santize
import app.cloudcoffee.securepassword.framework.Maybe
import app.cloudcoffee.securepassword.framework.NULL_STRING
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import kotlinx.serialization.json.Json

class UnencryptedPassword(val title: Title,
                          val username: Username,
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

        fun of(title: String? = null,
               username: String? = null,
               password: String? = null,
               email: String? = null,
               note: String? = null): UnencryptedPassword {

            return UnencryptedPassword(
                Title(title.santize()),
                Username(username.santize()),
                Password(password.santize()),
                Email(email.santize()),
                Note(note.santize()))
        }
    }
}