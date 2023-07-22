package app.cloudcoffee.securepassword.framework

interface What<T> {
    val value: T
}

fun <T> What<T>.isValue(compare: T): Boolean {
    return this.value?.equals(compare) ?: false
}