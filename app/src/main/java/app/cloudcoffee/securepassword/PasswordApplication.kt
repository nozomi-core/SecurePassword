package app.cloudcoffee.securepassword

import android.app.Application
import com.google.firebase.FirebaseApp

class PasswordApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}