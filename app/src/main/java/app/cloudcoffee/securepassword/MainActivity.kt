package app.cloudcoffee.securepassword

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.cloudcoffee.securepassword.screen.UINavigation
import app.cloudcoffee.securepassword.screen.initExampleApplication
import app.cloudcoffee.securepassword.theme.Retro3
import app.cloudcoffee.securepassword.theme.Retro4

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Retro4,
                    modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = UINavigation.START_NAVIGATION) {
                        initExampleApplication(this, navController)
                    }
                }
            }
        }
    }
}
