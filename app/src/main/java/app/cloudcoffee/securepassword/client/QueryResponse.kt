package app.cloudcoffee.securepassword.client

interface VirtualPointer<T> {
    val value: T
    fun <R> map(mapper: (input: T) -> R): VirtualPointer<R>
}

abstract class QueryResponse {
    abstract fun <T> convertResponse(aClazz: Class<T>): List<VirtualPointer<T>>

}