package app.cloudcoffee.securepassword.screen.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.screen.ScreenContext


@Composable
fun ExampleScreen(screenContext: ScreenContext) {
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            screenContext.navigation.goToAddPassword()
        }) {
            Text(text = "Add Account")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            if(password.value == "Boulos#187") {
                screenContext.navigation.goToDisplayPassword()
                password.value = ""
            }

        }) {
            Text(text = "Display Passwords")
        }
        BasicTextField(
            value = password.value,
            onValueChange =  {
            password.value = it
        })
    }
}

@Composable
fun ListId(state: List<String>) {
    
}