package app.cloudcoffee.securepassword.helper.hex

data class HexString(val encodedHex: String) {

    fun toByteArray(): ByteArray {
        return HexUtil.fromHex(encodedHex)
    }
}