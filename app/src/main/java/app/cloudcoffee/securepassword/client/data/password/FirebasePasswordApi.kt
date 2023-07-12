package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.client.DatabaseClient
import app.cloudcoffee.securepassword.client.ObjectCollection
import app.cloudcoffee.securepassword.framework.Maybe
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private val PASSWORD_PATH = ObjectCollection("app/secure-password/_exam")

class FirebasePasswordApi: PasswordApi, KoinComponent {
    private val client: DatabaseClient by inject()

    override suspend fun insert(password: EncryptedPassword): Maybe<Unit> {
        val hexPassword = password.convertPasswordToHex()
        val hexIv = password.convertIvToHex()

        val dto = PasswordDTO(
            password = hexPassword.encodedHex,
            iv = hexIv.encodedHex)

        return client.insert(PASSWORD_PATH, dto).toUnit()
    }

    override suspend fun insert(password: UnencryptedPassword): Maybe<Unit> {
        return password.encrypt().transformSuspend {
            insert(it)
        }
    }

    override suspend fun getAllPasswords(): Maybe<PasswordList> {
        return client.whereAny(PASSWORD_PATH).transformSuspend { response ->
            val listPasswords = response.convertResponse(PasswordDTO::class.java)
            val mapped = listPasswords.map { PasswordMapper.toUnencrypted(it) }

            PasswordList.fromMaybes(mapped)
        }
    }
}