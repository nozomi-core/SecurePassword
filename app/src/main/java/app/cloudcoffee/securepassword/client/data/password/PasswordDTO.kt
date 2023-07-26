package app.cloudcoffee.securepassword.client.data.password

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordDTO(
    @SerialName("password")
    val password: String?,
    @SerialName("iv_16")
    val iv: String?,
    val createdAt: Long?) {

    constructor(): this(null, null, null)
}
