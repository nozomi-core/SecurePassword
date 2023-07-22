package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.Maybe

class PasswordList(val list: List<UnencryptedPassword>) {

    fun whereEmail(email: String) {

    }

    companion object {
        fun fromMaybes(list: List<Maybe<UnencryptedPassword>>): PasswordList {
            val nonNulls = list.mapNotNull { it.getOrNull() }
            return PasswordList(nonNulls)
        }
    }
}