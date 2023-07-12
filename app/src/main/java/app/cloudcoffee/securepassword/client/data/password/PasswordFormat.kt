package app.cloudcoffee.securepassword.client.data.password

import kotlinx.serialization.Serializable

@Serializable
data class PasswordFormat(
    val username: String,
    val password: String,
    val email: String,
    val note: String)