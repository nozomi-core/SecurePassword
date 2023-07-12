package app.cloudcoffee.securepassword.client


interface ResponseConvertor<R, O>

abstract class QueryResponse {
    abstract fun <T> convertResponse(aClazz: Class<T>): List<T>
}