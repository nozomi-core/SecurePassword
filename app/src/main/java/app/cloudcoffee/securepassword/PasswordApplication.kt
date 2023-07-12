package app.cloudcoffee.securepassword

import android.app.Application
import app.cloudcoffee.securepassword.di.API_MODULES
import app.cloudcoffee.securepassword.di.CLIENT_MODULES
import com.google.firebase.FirebaseApp
import org.koin.core.context.startKoin

class PasswordApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        startKoin {
            modules(
                API_MODULES,
                CLIENT_MODULES)
        }
    }
}