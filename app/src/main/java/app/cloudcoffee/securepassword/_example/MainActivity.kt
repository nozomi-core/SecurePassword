package app.cloudcoffee.securepassword._example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.cloudcoffee.securepassword.screen.UINavigation
import app.cloudcoffee.securepassword.screen.initExampleApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController , startDestination = UINavigation.START_NAVIGATION) {
                initExampleApplication(this, navController)
            }
        }
    }
}
