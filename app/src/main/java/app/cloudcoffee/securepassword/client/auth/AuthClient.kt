package app.cloudcoffee.securepassword.client.auth

import app.cloudcoffee.securepassword.framework.Something

interface AuthClient {

    suspend fun loginWithEmail(email: String, password: String): Something<UserId>
}