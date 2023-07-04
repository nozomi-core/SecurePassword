package app.cloudcoffee.securepassword.client

import com.google.firebase.firestore.QuerySnapshot

class FirebaseQueryResponse(private val snapshot: QuerySnapshot): QueryResponse() {
}