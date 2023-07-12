package app.cloudcoffee.securepassword.client

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseDatabaseClient: DatabaseClient {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun insert(collection: ObjectCollection, value: Any): Maybe<ObjectResponse> {
        return suspendCoroutine { continuation ->
            firestore.collection(collection.path).add(value).addOnSuccessListener { reference ->
                continuation.resume(Maybe.value(ObjectResponse(ObjectPath(reference.path))))
            }.addOnFailureListener {
                continuation.resume(Maybe.fail(it, FailureCode.NETWORK_EXCEPTION))
            }.addOnCanceledListener {
                continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
            }
        }
    }

    override suspend fun whereEqualTo(
        collection: ObjectCollection,
        key: String,
        value: Any
    ): Maybe<QueryResponse> {
        return suspendCoroutine { continuation ->
            firestore.collection(collection.path).whereEqualTo(key, value).get().addOnSuccessListener {
                continuation.resume(Maybe.value(FirebaseQueryResponse(it)))
            }.addOnFailureListener {
                continuation.resume(Maybe.fail(it, FailureCode.NETWORK_EXCEPTION))
            }.addOnCanceledListener {
                continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
            }
        }
    }

    override suspend fun whereAny(collection: ObjectCollection): Maybe<QueryResponse> {
        return suspendCoroutine { continuation ->
            firestore.collection(collection.path).get().addOnSuccessListener {
                continuation.resume(Maybe.value(FirebaseQueryResponse(it)))
            }.addOnFailureListener {
                continuation.resume(Maybe.fail(it, FailureCode.NETWORK_EXCEPTION))
            }.addOnCanceledListener {
                continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
            }
        }
    }

    override suspend fun set(path: ObjectPath, value: Any): Maybe<Unit> {
        return suspendCoroutine { continuation ->
        firestore.document(path.fullPath).set(value).addOnSuccessListener {
                continuation.resume(Maybe.value(Unit))
            }.addOnFailureListener {
                continuation.resume(Maybe.fail(it, FailureCode.NETWORK_EXCEPTION))
            }.addOnCanceledListener {
                continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED))
            }
        }
    }
}