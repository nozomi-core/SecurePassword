package app.cloudcoffee.securepassword._example

import app.cloudcoffee.securepassword._env.testSignal
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FirebaseExample {

    @Test
    fun testFirebase() {
        testSignal<String> { signal ->
            val firestore = FirebaseFirestore.getInstance()

            firestore.collection("app/secure-password/_examples").add(mutableMapOf<String, Any>().apply {
                put("popcorn", "again")
            }).addOnSuccessListener {
                signal.done(it.id)
            }
        }
    }
}