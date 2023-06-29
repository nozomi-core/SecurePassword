package app.cloudcoffee.securepassword.framework

sealed class Something<out T> {
    class Value<out T> private constructor(val theValue: T): Something<T>() {
        companion object {
            fun <T> create(wrapped: T?): Something<T> {
                return if(wrapped != null)
                    Value(wrapped)
                else
                    Failed.create(FailCode.NULL_VALUE, null)
            }
        }
    }
    class Failed private constructor(val code: FailCode, val throwable: Throwable?): Something<Nothing>() {
        //TODO: handle code and throwable later
        companion object {
            fun create(code: FailCode, throwable: Throwable?): Something<Nothing> {
                return Failed(code, throwable)
            }
        }
    }

    fun get(onValue: (myVal: T) -> Unit) {
        when(this) {
            is Value -> onValue(theValue)
            else -> {}
        }
    }

    fun fail(onFail: () -> Unit) {
        when(this) {
            is Failed -> onFail()
            else -> {}
        }
    }

    fun failWith(onFail: (code: FailCode, ex: Throwable?) -> Unit) {
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
                    fail(e, FailCode.GENERAL)
                }
            }
            is Failed -> this
        }
    }

    fun <R> flatMap(mapper: (T) -> Something<R>): Something<R> {
        return when (this) {
            is Value -> mapper(theValue)
            is Failed -> this
        }
    }

    companion object {

        fun <T> wrap (aValue: T?): Something<T> {
            return  Value.create(aValue)
        }

        fun fail(throwable: Throwable, code: FailCode): Something<Nothing> {
            return Failed.create(code, throwable)
        }

        fun fail(code: FailCode): Something<Nothing>  {
            return Failed.create(code, null)
        }
    }
}