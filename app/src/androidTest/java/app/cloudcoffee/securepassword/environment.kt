package app.cloudcoffee.securepassword

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.nio.charset.Charset

@RunWith(JUnit4::class)
class environment {

    @Test
    fun testDefaultEncoding() {
        Assert.assertEquals(Charset.defaultCharset(), Charsets.UTF_8)
    }
}