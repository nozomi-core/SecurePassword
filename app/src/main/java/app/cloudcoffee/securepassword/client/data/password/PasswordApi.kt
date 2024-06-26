package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.client.VirtualPointer
import app.cloudcoffee.securepassword.framework.Maybe

interface PasswordApi {
    suspend fun insert(password: EncryptedPassword): Maybe<Unit>
    suspend fun insert(password: UnencryptedPassword): Maybe<Unit>
    suspend fun getAllPasswords(): Maybe<PasswordList>
    suspend fun delete(pointer: VirtualPointer<UnencryptedPassword>): Maybe<Unit>
}