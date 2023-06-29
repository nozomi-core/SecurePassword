package app.cloudcoffee.securepassword.security.aes

import app.cloudcoffee.securepassword.security.hex.HexString
import app.cloudcoffee.securepassword.security.hex.HexUtil

data class AesDecryptResult(val payload: ByteArray) {

    fun toHexString(): HexString {
        return HexUtil.toHexString(payload)
    }

    fun toUtf8String(): String {
        return String(payload, Charsets.UTF_8)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AesDecryptResult

        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        return payload.contentHashCode()
    }

}