package app.cloudcoffee.securepassword.client

import com.google.firebase.firestore.QuerySnapshot

class FirebaseQueryResponse(private val snapshot: QuerySnapshot): QueryResponse() {

    override fun <T> convertResponse(aClazz: Class<T>): List<T> {
        return snapshot.documents.mapNotNull { documentSnapshot ->
            try {
                documentSnapshot.toObject(aClazz)
            } catch (e: Throwable) {
                null
            }
        }
    }
}