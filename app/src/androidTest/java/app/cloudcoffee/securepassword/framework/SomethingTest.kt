package app.cloudcoffee.securepassword.framework

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SomethingTest {

    @Test
    fun testNullString() {
        val nullString: String? = null
        val pop  = Maybe.value(nullString)
        Assert.assertTrue(pop is Maybe.Null)
    }

    @Test
    fun testString() {
        val aString: String = ""
        val pop  = Maybe.value(aString)
        Assert.assertTrue(pop is Maybe.Value)
    }

    @Test
    fun stringNullLength() {
        val nullString: String? = null
        val someString  = Maybe.value(nullString)
        val stringLength = getCharLength(someString)
        Assert.assertTrue(stringLength is Maybe.Null)
    }
    @Test
    fun stringLength() {
        val nullString: String = "123"
        val someString  = Maybe.value(nullString)
        val stringLength = getCharLength(someString)
        val theValue = stringLength.getOrNull()

        Assert.assertTrue(stringLength is Maybe.Value)
        Assert.assertEquals(3, theValue)
    }

    @Test
    fun testThrowTransform() {
        val theString: String = "123"
        val someString  = Maybe.value(theString)
        val count = someString.transform {
            throwWithError("test throw transform fail")
            it.length
        }
        Assert.assertTrue(count is Maybe.Failed)
    }

    @Test
    fun testFlatMap() {
        val myString = getSomething("welcome")
        val countme = myString.then {
            Maybe.value(it.length)
        }
        Assert.assertTrue(countme is Maybe.Value)
    }
    
    @Test
    fun testBind() {
        getSomething("hello")
            .transform { it.length }
            .transform { "number:${it}" }.onValue { 
                
            }.onErrorWith { code, ex ->  
                
            }
    }

    fun getSomething(value: String?): Maybe<String> {
        return Maybe.value(value)
    }


    fun getCharLength(string: Maybe<String>): Maybe<Int> {
        return string.transform {
            it.length
        }
    }

    fun throwWithError(msg: String) {
        throw Error(msg)
    }
}