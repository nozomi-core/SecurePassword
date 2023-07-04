package app.cloudcoffee.securepassword.client.auth

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthClient: AuthClient {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun loginWithEmail(email: String, password: String): Maybe<UserId> {
        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener { authResult ->
                val userId = authResult.user?.uid
                if(userId != null)
                    continuation.resume(Maybe.value(UserId(userId)))
                else
                    continuation.resume(Maybe.fail(FailureCode.NULL_VALUE, "user id is null"))

            }.addOnFailureListener {
                continuation.resume(Maybe.fail(it, FailureCode.NETWORK_EXCEPTION))
            }.addOnCanceledListener {
                continuation.resume(Maybe.fail(FailureCode.OPERATION_CANCELED, "firebase login canceled"))
            }
        }
    }
}