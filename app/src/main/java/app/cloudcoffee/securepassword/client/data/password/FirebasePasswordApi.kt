package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe

class FirebasePasswordApi: PasswordApi {
    override suspend fun insert(password: EncryptedPassword): Maybe<Unit> {
        TODO("Not yet implemented")
    }
}