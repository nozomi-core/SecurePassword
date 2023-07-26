package app.cloudcoffee.securepassword

import android.app.Application
import app.cloudcoffee.securepassword.client.clock.ClockApi
import app.cloudcoffee.securepassword.di.API_MODULES
import app.cloudcoffee.securepassword.di.CLIENT_MODULES
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

typealias ApplicationReady = () -> Unit

class PasswordApplication: Application(), KoinComponent {

    private val clockApi: ClockApi by inject()

    private val readyCallbacks: MutableSet<ApplicationReady> = mutableSetOf()

    override fun onCreate() {
        super.onCreate()
        initializeApplication()
    }

    fun onApplicationReady(callback: ApplicationReady) {
        readyCallbacks.add(callback)
    }

    private fun initializeApplication() {
        FirebaseApp.initializeApp(this)

        startKoin {
            modules(
                API_MODULES,
                CLIENT_MODULES)
        }

        //Initialise any Async tasks
        GlobalScope.launch(Dispatchers.IO) {
            //Sync application server time
            clockApi.pingServerForTime()


            //notify any ready observers on application initialized
            readyCallbacks.forEach { it() }
            readyCallbacks.clear()
        }
    }
}