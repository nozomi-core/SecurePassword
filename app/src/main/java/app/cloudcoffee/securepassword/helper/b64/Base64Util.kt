package app.cloudcoffee.securepassword.helper.b64

import android.util.Base64

data class Base64String(val encoded: String)

object Base64Util {

    fun encode(byteArray: ByteArray): Base64String {
        val encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP or Base64.URL_SAFE)
        return Base64String(encoded)
    }

    fun decode(b64String: Base64String): ByteArray {
        val encoded = Base64.decode(b64String.encoded, Base64.NO_WRAP or Base64.URL_SAFE)
        return encoded
    }
}