package app.cloudcoffee.securepassword.client.clock

import app.cloudcoffee.securepassword.extensions.fetchDocument
import app.cloudcoffee.securepassword.extensions.insert
import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Clock

class FirebaseClockApi: ClockApi {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun pingServerForTime(): Maybe<ClockResponse> {
        val jsonInput = mutableMapOf<String, Any>()
        jsonInput.put("server_time", FieldValue.serverTimestamp())

        val clientRequestTime = Clock.systemUTC().millis()

        val ref = firestore.insert("ping", jsonInput)
        val clockResult = ref.thenSuspend {
            it.fetchDocument()
        }.then { snapshot ->
            val serverTime = snapshot.getTimestamp("server_time")?.toDate()?.time
            if(serverTime == null) {
                Maybe.fail(FailureCode.DOMAIN_ERROR)
            }
            else {
                val clientRequestDelta = Clock.systemUTC().millis() - clientRequestTime

                ref.onValue { it.delete() }
                Maybe.value(ClockResponse(clientRequestTime, serverTime, clientRequestDelta))
            }
        }
        return clockResult.apply {
            onValue { ClientTime.setServerClockResponse(it) }
        }
    }
}