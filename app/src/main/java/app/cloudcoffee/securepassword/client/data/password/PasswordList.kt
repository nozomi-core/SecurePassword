package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.client.VirtualPointer

class PasswordList(val list: List<VirtualPointer<UnencryptedPassword>>) {

    fun orderByTitleDesc(): PasswordList {
        val nextList = mutableListOf<VirtualPointer<UnencryptedPassword>>().run {
            addAll(list)
            sortedBy { it.value.title.toString() }
        }
        return PasswordList(nextList)
    }
}