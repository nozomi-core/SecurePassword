package app.cloudcoffee.securepassword.security.aes

import app.cloudcoffee.securepassword.security.hex.HexString
import app.cloudcoffee.securepassword.security.hex.HexUtil

data class AesEncryptResult(val payload: ByteArray) {

    fun toHexString(): HexString {
        return HexUtil.toHexString(payload)
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