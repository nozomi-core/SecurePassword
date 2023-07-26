package app.cloudcoffee.securepassword.client

import com.google.firebase.firestore.QuerySnapshot

class FirebaseQueryResponse(private val snapshot: QuerySnapshot): QueryResponse() {

    override fun <T> convertResponse(aClazz: Class<T>): List<VirtualPointer<T>> {
        return snapshot.documents.mapNotNull {
            FirebaseVirtualPointer.of(it, aClazz)
        }
    }
}