package app.cloudcoffee.securepassword.example

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class ExampleTest {

    @Test
    fun testExample() {
        val myDate = "2022-03-12"

        val localDate = parseDate(myDate)
        Assert.assertTrue(localDate is Maybe.Value)
    }

    @Test
    fun testExampleDays() {
        val myDate = "2022-03-12"

        val localDate = parseDate(myDate)
        val years = getYear(localDate)
        val count = addYears(years, 34)
        val pop = convertString(count)

        Assert.assertTrue(pop is Maybe.Value)
    }

    @Test
    fun moreTest() {

    }

    private fun getYear(date: Maybe<LocalDate>): Maybe<Int> {
        return date.transform {
            it.year
        }
    }

    private fun addYears(input: Maybe<Int>, param: Int): Maybe<Int> {
        return input.then {
           val requested = it + param

            if(requested > 2022)
                Maybe.fail(FailureCode.DOMAIN_ERROR, "cannot add more then 2022 years")
            else
                Maybe.value(requested)
        }
    }

    private fun convertString(input: Maybe<Int>): Maybe<String> {
        return input.transform {
            "convert: $it"
        }
    }

    private fun parseDate(date: String): Maybe<LocalDate> {
        return try {
            val parsedDate = LocalDate.parse(date)
            Maybe.value(parsedDate)
        } catch (e: Throwable) {
            Maybe.fail(e, FailureCode.PARSING_ERROR)
        }
    }
}