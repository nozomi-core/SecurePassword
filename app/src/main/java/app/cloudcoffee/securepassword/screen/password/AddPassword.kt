package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.framework.NULL_STRING
import app.cloudcoffee.securepassword.screen.ScreenContext


@Composable
@Preview
fun AddPasswordScreen(screenContext: ScreenContext? = null) {
    val passwordViewModel = viewModel { AddPasswordViewModel() }

    val title = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val note = remember { mutableStateOf("") }

    Column(modifier = Modifier.background(Color.White)) {
        PasswordInput(name = "Title", state = title)
        PasswordInput(name = "Username", state = username)
        PasswordInput(name = "Password", state = password)
        PasswordInput(name = "Email", state =  email)
        PasswordInput(name = "Note", state = note)

        Button(onClick = {
            passwordViewModel.addPassword(
                title = title.value,
                username = username.value,
                password = password.value,
                note = note.value,
                email = email.value)

            title.value = NULL_STRING
            username.value = NULL_STRING
            password.value = NULL_STRING
            email.value = NULL_STRING
            note.value = NULL_STRING

        }) {
            Text(modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Submit Password")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(name: String, state: MutableState<String>) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = name)
    TextField(
        value = state.value,
        onValueChange = { state.value = it }, modifier = with(Modifier) {
            padding(16.dp)
            background(Color.Cyan)
            fillMaxWidth()
    }, colors = TextFieldDefaults.textFieldColors())
}