package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.client.VirtualPointer
import app.cloudcoffee.securepassword.framework.Maybe

class PasswordList(val list: List<VirtualPointer<UnencryptedPassword>>) {

    fun whereEmail(email: String) {
        //TODO
    }

    fun orderByTitleDesc(): List<VirtualPointer<UnencryptedPassword>> {
        return mutableListOf<VirtualPointer<UnencryptedPassword>>().run {
            addAll(list)
            sortedBy { it.value.title.toString() }
        }
    }

    companion object {
        fun fromMaybes(list: List<VirtualPointer<Maybe<UnencryptedPassword>>>): PasswordList {
            val result = list.map { pointer ->
                pointer.map {
                    it.getOrNull()
                }
            }
            val notNull = mutableListOf<VirtualPointer<UnencryptedPassword>>()
            result.forEach { virtualPointer ->
                (virtualPointer as? VirtualPointer<UnencryptedPassword>)?.let {
                    notNull.add(it)
                }
            }
            return PasswordList(notNull)
        }
    }
}