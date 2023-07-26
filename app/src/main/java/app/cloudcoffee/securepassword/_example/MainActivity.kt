package app.cloudcoffee.securepassword._example

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.cloudcoffee.securepassword.PasswordApplication
import app.cloudcoffee.securepassword.client.clock.ClientTime
import app.cloudcoffee.securepassword.screen.UINavigation
import app.cloudcoffee.securepassword.screen.initExampleApplication
import java.time.Clock

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController , startDestination = UINavigation.START_NAVIGATION) {
                    initExampleApplication(this, navController)
                }
            }
        }

        val application = applicationContext as PasswordApplication
        application.onApplicationReady {
            val myTime = ClientTime.serverUTC
            val time = Clock.systemUTC().millis()
            val myMillis = System.currentTimeMillis()
            val gnnsTime = SystemClock.currentGnssTimeClock().millis()
        }
    }
}
