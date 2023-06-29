package app.cloudcoffee.securepassword.example

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Something
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class ExampleTest {

    @Test
    fun testExample() {
        val myDate = "2022-03-12"

        val localDate = parseDate(myDate)
        Assert.assertTrue(localDate is Something.Value)
    }

    @Test
    fun testExampleDays() {
        val myDate = "2022-03-12"

        val localDate = parseDate(myDate)
        val years = getYear(localDate)
        val count = addYears(years, 34)
        val pop = convertString(count)

        Assert.assertTrue(pop is Something.Value)
    }

    private fun getYear(date: Something<LocalDate>): Something<Int> {
        return date.transform {
            it.year
        }
    }

    private fun addYears(input: Something<Int>, param: Int): Something<Int> {
        return input.flatMap {
           val requested = it + param

            if(requested > 2022)
                Something.fail(FailureCode.DOMAIN_ERROR, "cannot add more then 2022 years")
            else
                Something.wrap(requested)
        }
    }

    private fun convertString(input: Something<Int>): Something<String> {
        return input.transform {
            "convert: $it"
        }
    }

    private fun parseDate(date: String): Something<LocalDate> {
        return try {
            val parsedDate = LocalDate.parse(date)
            Something.wrap(parsedDate)
        } catch (e: Throwable) {
            Something.fail(e, FailureCode.PARSING_ERROR)
        }
    }
}