package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import app.cloudcoffee.securepassword.helper.hex.HexUtil
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import kotlinx.serialization.json.Json
import javax.crypto.spec.IvParameterSpec

object PasswordMapper {

    private val jsonBuilder = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun toFormat(model: UnencryptedPassword): PasswordFormat {
        return PasswordFormat(
            username = model.username.value,
            password = model.password.value,
            email = model.email.value,
            note = model.note.value
        )
    }

    fun toPassword(format: PasswordFormat): UnencryptedPassword {
        return UnencryptedPassword.of(
            username = format.username,
            password = format.password,
            email = format.email,
            note = format.note
        )
    }

    fun toUnencrypted(dto: PasswordDTO): Maybe<UnencryptedPassword> {
        if(dto.iv == null || dto.password == null)
            return Maybe.fail(FailureCode.NULL_VALUE)

        val iv = HexUtil.fromHex(dto.iv)
        val word = HexUtil.fromHex(dto.password)

        return AesEncryption.decrypt(word, IvParameterSpec(iv)).transform { result ->
            val json = result.toUtf8String()
            val next = jsonBuilder.decodeFromString(PasswordFormat.serializer(), json)
            toPassword(next)
        }
    }
}