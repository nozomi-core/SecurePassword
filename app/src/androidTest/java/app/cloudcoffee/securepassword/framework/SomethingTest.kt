package app.cloudcoffee.securepassword.framework

import org.junit.Assert
import org.junit.Test

class SomethingTest {

    @Test
    fun testNullString() {
        val nullString: String? = null
        val pop  = Something.wrap(nullString)
        Assert.assertTrue(pop is Something.Failed)
    }

    @Test
    fun testString() {
        val aString: String = ""
        val pop  = Something.wrap(aString)
        Assert.assertTrue(pop is Something.Value)
    }

    @Test
    fun stringNullLength() {
        val nullString: String? = null
        val someString  = Something.wrap(nullString)
        val stringLength = getCharLength(someString)
        Assert.assertTrue(stringLength is Something.Failed)
    }
    @Test
    fun stringLength() {
        val nullString: String = "123"
        val someString  = Something.wrap(nullString)
        val stringLength = getCharLength(someString)
        val theValue = stringLength.getOrNull()

        Assert.assertTrue(stringLength is Something.Value)
        Assert.assertEquals(3, theValue)
    }

    @Test
    fun testThrowTransform() {
        val theString: String = "123"
        val someString  = Something.wrap(theString)
        val count = someString.transform {
            throwWithError("test throw transform fail")
            it.length
        }
        Assert.assertTrue(count is Something.Failed)
    }

    @Test
    fun testFlatMap() {
        val myString = getSomething("welcome")
        val countme = myString.flatMap {
            Something.wrap(it.length)
        }
        Assert.assertTrue(countme is Something.Value)
    }

    fun getSomething(value: String?): Something<String> {
        return Something.wrap(value)
    }


    fun getCharLength(string: Something<String>): Something<Int> {
        return string.transform {
            it.length
        }
    }

    fun throwWithError(msg: String) {
        throw Error(msg)
    }
}