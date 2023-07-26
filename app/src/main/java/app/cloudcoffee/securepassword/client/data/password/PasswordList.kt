package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.client.VirtualPointer

class PasswordList(val list: List<VirtualPointer<UnencryptedPassword>>) {

    fun orderByTitleDesc(): List<VirtualPointer<UnencryptedPassword>> {
        return mutableListOf<VirtualPointer<UnencryptedPassword>>().run {
            addAll(list)
            sortedBy { it.value.title.toString() }
        }
    }
}