package app.cloudcoffee.securepassword.screen.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.screen.ScreenContext


@Composable
fun ExampleScreen(screenContext: ScreenContext) {
    Column {
        Button(onClick = {
            screenContext.navigation.goToAddPassword()
        }) {
            Text(text = "Add Account")
        }
        Button(onClick = {
            screenContext.navigation.goToDisplayPassword()
        }) {
            Text(text = "Display Passwords")
        }
    }
}

@Composable
fun ListId(state: List<String>) {
    
}