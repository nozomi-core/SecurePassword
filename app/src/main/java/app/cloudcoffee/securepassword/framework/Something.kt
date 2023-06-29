package app.cloudcoffee.securepassword.framework

sealed class Something<out T> {
    class Value<out T> private constructor(val theValue: T): Something<T>() {
        companion object {
            fun <T> create(wrapped: T?): Something<T> {
                return if(wrapped != null)
                    Value(wrapped)
                else
                    Failed.create(FailureCode.NULL_VALUE, null)
            }
        }
    }

    class Failed private constructor(val code: FailureCode, val throwable: Throwable?): Something<Nothing>() {
        companion object {
            fun create(code: FailureCode, throwable: Throwable?): Something<Nothing> {
                return Failed(code, throwable)
            }
        }
    }

    fun onValue(onValue: (myVal: T) -> Unit) {
        when(this) {
            is Value -> onValue(theValue)
            else -> {}
        }
    }

    fun onError(onFail: () -> Unit) {
        when(this) {
            is Failed -> onFail()
            else -> {}
        }
    }

    fun onErrorWith(onFail: (code: FailureCode, ex: Throwable?) -> Unit) {
        when(this) {
            is Failed -> onFail(code, throwable)
            else -> {}
        }
    }

    fun getOrNull(): T? {
        return when(this) {
            is Value -> theValue
            else -> null
        }
    }

    fun <R> transform(mapper: (myVal: T) -> R): Something<R> {
        return when(this) {
            is Value -> {
                try {
                    wrap(mapper(theValue))
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
        }
    }

    fun <R> flatMap(mapper: (T) -> Something<R>): Something<R> {
        return when (this) {
            is Value -> {
                try {
                    mapper(theValue)
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
        }
    }

    companion object {

        fun <T> wrap (aValue: T?): Something<T> {
            return  Value.create(aValue)
        }

        fun fail(throwable: Throwable, code: FailureCode): Something<Nothing> {
            return Failed.create(code, throwable)
        }

        fun fail(code: FailureCode, message: String): Something<Nothing> {
            return Failed.create(code, Error(message))
        }

        fun fail(code: FailureCode): Something<Nothing>  {
            return Failed.create(code, null)
        }
    }
}