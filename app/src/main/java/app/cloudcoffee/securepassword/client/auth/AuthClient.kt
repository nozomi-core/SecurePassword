package app.cloudcoffee.securepassword.client.auth

import app.cloudcoffee.securepassword.framework.Maybe

interface AuthClient {
    suspend fun loginWithEmail(email: String, password: String): Maybe<UserId>
}