package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe
import app.cloudcoffee.securepassword.helper.hex.HexString
import app.cloudcoffee.securepassword.helper.hex.HexUtil
import app.cloudcoffee.securepassword.security.aes.AesEncryptResult
import app.cloudcoffee.securepassword.security.aes.AesEncryption
import kotlinx.serialization.json.Json
import javax.crypto.spec.IvParameterSpec

class EncryptedPassword(val ivParameterSpec: IvParameterSpec, val encoded: ByteArray) {

    fun decrypt(): Maybe<UnencryptedPassword> {
        return AesEncryption.decrypt(encoded, ivParameterSpec).transform { decryptResult ->
            val format = Json.decodeFromString(PasswordFormat.serializer(), decryptResult.toUtf8String())
            PasswordMapper.toPassword(format)
        }
    }

    fun convertIvToHex(): HexString = HexUtil.toHexString(ivParameterSpec.iv)
    fun convertPasswordToHex(): HexString = HexUtil.toHexString(encoded)


}