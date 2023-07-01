package app.cloudcoffee.securepassword.security.aes

import app.cloudcoffee.securepassword.helper.b64.Base64String
import app.cloudcoffee.securepassword.helper.b64.Base64Util
import app.cloudcoffee.securepassword.helper.hex.HexString
import app.cloudcoffee.securepassword.helper.hex.HexUtil
import javax.crypto.spec.IvParameterSpec

data class AesEncryptResult(val payload: ByteArray, val ivParameterSpec: IvParameterSpec) {

    fun payloadToHex(): HexString {
        return HexUtil.toHexString(payload)
    }

    fun payloadToBase64(): Base64String {
        return Base64Util.encode(payload)
    }

    fun ivToHex(): HexString {
        return HexUtil.toHexString(ivParameterSpec.iv)
    }

    fun ivToBase64(): Base64String {
        return Base64Util.encode(ivParameterSpec.iv)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AesEncryptResult

        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        return payload.contentHashCode()
    }
}