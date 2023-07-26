package app.cloudcoffee.securepassword.extensions

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun FirebaseFirestore.fetchWithPath(path: String): Maybe<DocumentSnapshot> {
    return suspendCoroutine { continuation ->
        document(path).get().addOnSuccessListener {
            continuation.resume(Maybe.value(it))
        }.addOnFailureListener {
            continuation.resume(Maybe.fail(FailureCode.NETWORK_EXCEPTION))
        }.addOnCanceledListener {
            continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
        }
    }
}

suspend fun FirebaseFirestore.insert(collection: String, map: Map<String, Any>): Maybe<DocumentReference> {
    return suspendCoroutine { continuation ->
        collection(collection).add(map).addOnSuccessListener {
            continuation.resume(Maybe.value(it))
        }.addOnFailureListener {
            continuation.resume(Maybe.fail(FailureCode.NETWORK_EXCEPTION))
        }.addOnCanceledListener {
            continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
        }
    }
}

suspend fun DocumentReference.fetchDocument(): Maybe<DocumentSnapshot> {
    return firestore.fetchWithPath(path)
}