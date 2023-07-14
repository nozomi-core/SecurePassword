package app.cloudcoffee.securepassword.extensions

fun String?.santize(): String {
    if(this == null)
        return ""
    else
        return trim()
}

fun <T> T?.santizeDefault(defaultValue: T): T {
    if(this == null)
        return defaultValue
    else
        return this
}