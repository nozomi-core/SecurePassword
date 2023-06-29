package app.cloudcoffee.securepassword.security

import app.cloudcoffee.securepassword.security.hex.HexUtil
import org.junit.Assert
import org.junit.Test


class HexUtilTest {

    @Test
    fun testHexConvert() {
        val result = HexUtil.toHexString("hello hex-world".toByteArray(Charsets.UTF_8))
        Assert.assertEquals("68656c6c6f206865782d776f726c64", result.encodedHex)
    }

    @Test
    fun testToString() {
        val result = HexUtil.fromHex("68656c6c6f206865782d776f726c64")
        Assert.assertEquals("hello hex-world", String(result, Charsets.UTF_8))
    }
}