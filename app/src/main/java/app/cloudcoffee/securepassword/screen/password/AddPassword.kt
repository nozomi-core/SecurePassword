package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.screen.ScreenContext
import app.cloudcoffee.securepassword.screen.example.ExampleViewModel

@Composable
fun AddPasswordScreen(screenContext: ScreenContext) {
    val passwordViewModel = viewModel { AddPasswordViewModel() }

    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column {
        PasswordInput(name = "Username", state = username)
        PasswordInput(name = "Password", state = password)

        Button(onClick = {
            passwordViewModel.addPassword(username.value, password.value)

        }) {
            Text(text = "Sumit Password")
        }
    }
}

@Composable
fun PasswordInput(name: String, state: MutableState<String>) {
    Text(text = name)
    BasicTextField(value = state.value, onValueChange = { state.value = it }, modifier = with(Modifier) {
        background(Color.Cyan)
    })
}