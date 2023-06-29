package app.cloudcoffee.securepassword.security

object HexUtil {

    private val hexArray = "0123456789abcdef".toCharArray()

    fun toHexString(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (i in bytes.indices) {
            val v = bytes[i].toInt() and 0xFF
            hexChars[i * 2] = hexArray[v.ushr(4)]
            hexChars[i * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

    fun fromHex(hex: String): ByteArray {
        return hex.chunked(2).map { it.toInt(16).toByte() }
            .toByteArray()
    }
}