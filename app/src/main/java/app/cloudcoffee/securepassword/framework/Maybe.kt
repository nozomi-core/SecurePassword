package app.cloudcoffee.securepassword.framework

sealed class Maybe<out T> {
    class Value<out T> private constructor(val theValue: T): Maybe<T>() {
        companion object {
            fun <T> create(wrapped: T?): Maybe<T> {
                return if(wrapped != null)
                    Value(wrapped)
                else
                    Null.create()
            }
        }
    }

    class Null private constructor(): Maybe<Nothing>() {
        companion object {
            fun create(): Maybe<Nothing> {
                return Null()
            }
        }
    }

    class Failed private constructor(val code: FailureCode, val throwable: Throwable?): Maybe<Nothing>() {
        companion object {
            fun create(code: FailureCode, throwable: Throwable?): Maybe<Nothing> {
                return Failed(code, throwable)
            }
        }
    }

    fun toUnit(): Maybe<Unit> {
        return when(this) {
            is Value -> value(Unit)
            else -> fail(FailureCode.SOMETHING_MAPPING)
        }
    }

    fun onValue(onValue: (myVal: T) -> Unit): Maybe<T> {
        when(this) {
            is Value -> onValue(theValue)
            else -> {}
        }
        return this
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

    fun <R> transform(mapper: (myVal: T) -> R): Maybe<R> {
        return when(this) {
            is Value -> {
                try {
                    value(mapper(theValue))
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
            is Null -> this
        }
    }

    suspend fun <R> transformSuspend(mapper: suspend (myVal: T) -> R): Maybe<R> {
        return when(this) {
            is Value -> {
                try {
                    value(mapper(theValue))
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
            is Null -> this
        }
    }

    fun <R> then(mapper: (T) -> Maybe<R>): Maybe<R> {
        return when (this) {
            is Value -> {
                try {
                    mapper(theValue)
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
            is Null -> this
        }
    }

    suspend fun <R> thenSuspend(mapper: suspend (T) -> Maybe<R>): Maybe<R> {
        return when (this) {
            is Value -> {
                try {
                    mapper(theValue)
                } catch (e: Throwable) {
                    fail(e, FailureCode.SOMETHING_MAPPING)
                }
            }
            is Failed -> this
            is Null -> this
        }
    }

    companion object {

        fun <T> value (aValue: T?): Maybe<T> {
            return  Value.create(aValue)
        }

        fun fail(throwable: Throwable, code: FailureCode): Maybe<Nothing> {
            return Failed.create(code, throwable)
        }

        fun fail(code: FailureCode, message: String): Maybe<Nothing> {
            return Failed.create(code, Error(message))
        }

        fun fail(code: FailureCode): Maybe<Nothing>  {
            return Failed.create(code, null)
        }
    }
}