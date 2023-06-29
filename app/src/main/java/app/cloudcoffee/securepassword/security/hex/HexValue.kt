package app.cloudcoffee.securepassword.security.hex

data class HexString(val encodedHex: String) {

    fun toByteArray(): ByteArray {
        return HexUtil.fromHex(encodedHex)
    }
}