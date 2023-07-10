package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe

interface PasswordApi {
    suspend fun insert(password: EncryptedPassword): Maybe<Unit>
}