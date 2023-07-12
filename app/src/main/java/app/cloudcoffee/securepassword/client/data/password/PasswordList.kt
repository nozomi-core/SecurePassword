package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe

class PasswordList(private val list: List<UnencryptedPassword>) {

    companion object {

        fun fromMaybes(list: List<Maybe<UnencryptedPassword>>): PasswordList {
            val nonNulls = list.mapNotNull { it.getOrNull() }
            return PasswordList(nonNulls)
        }
    }
}