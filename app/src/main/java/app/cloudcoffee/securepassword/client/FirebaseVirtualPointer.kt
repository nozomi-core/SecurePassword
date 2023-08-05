package app.cloudcoffee.securepassword.client

import com.google.firebase.firestore.DocumentSnapshot

class FirebaseVirtualPointer<T> private constructor(val documentPath: String, override val value: T): VirtualPointer<T> {

    override fun <R> map(mapper: (input: T) -> R): VirtualPointer<R> {
        val mappedValue = mapper(value)
        return FirebaseVirtualPointer(documentPath, mappedValue)
    }

    override fun <R> copyWith(another: R): VirtualPointer<R> {
        return FirebaseVirtualPointer(documentPath, another)
    }

    fun toObjectPath() = ObjectPath(documentPath)

    companion object {
        fun <T> of(snapshot: DocumentSnapshot, clazz: Class<T>): FirebaseVirtualPointer<T>? {
            return try {
                FirebaseVirtualPointer(snapshot.reference.path, snapshot.toObject(clazz)!!)
            } catch (e: Throwable) {
                null
            }
        }
    }
}