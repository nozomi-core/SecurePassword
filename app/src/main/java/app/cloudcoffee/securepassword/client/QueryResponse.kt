package app.cloudcoffee.securepassword.client

abstract class QueryResponse {
    abstract fun <T> convertResponse(aClazz: Class<T>): List<T>
}