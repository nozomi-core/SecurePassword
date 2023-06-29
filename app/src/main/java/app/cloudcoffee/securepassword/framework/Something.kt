package app.cloudcoffee.securepassword.framework

sealed class Something<out T> private constructor() {
    class Value<out T> private constructor(val theValue: T): Something<T>()
    class Error private constructor(): Something<Nothing>()


    companion object {

        fun <T> wrap (aValue: T): Something<T> {
            if(aValue != null)
                Something.Value(aValue)

        }
    }
}