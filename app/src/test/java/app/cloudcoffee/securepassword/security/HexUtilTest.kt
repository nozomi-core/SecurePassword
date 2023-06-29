package app.cloudcoffee.securepassword.security

import org.junit.Assert
import org.junit.Test


class HexUtilTest {

    @Test
    fun testHash() {
        val result = HexUtil.toHexString("popcorn".toByteArray(Charsets.UTF_8))
        Assert.assertEquals("706f70636f726e", result)
    }

    @Test
    fun fromHex() {
        val result = HexUtil.fromHex("706f70636f726e")
        Assert.assertEquals("popcorn", String(result, Charsets.UTF_8))
    }
}