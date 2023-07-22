package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cloudcoffee.securepassword.screen.ScreenContext

@Composable
fun PasswordScreen(screenContext: ScreenContext) {
    val state = remember {
        mutableStateOf(mutableListOf<String>())
    }
    Column {
        Text(text = "Add Password Screen")
    }
}