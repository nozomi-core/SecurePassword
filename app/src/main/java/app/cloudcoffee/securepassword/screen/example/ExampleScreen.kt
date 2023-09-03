package app.cloudcoffee.securepassword.screen.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.screen.ScreenContext
import app.cloudcoffee.securepassword.screen.password.DisplayPasswordViewModel
import app.cloudcoffee.securepassword.theme.DiscordBlack40
import app.cloudcoffee.securepassword.theme.DiscordBlue
import app.cloudcoffee.securepassword.theme.Retro1
import app.cloudcoffee.securepassword.theme.Retro2
import app.cloudcoffee.securepassword.theme.Retro3
import app.cloudcoffee.securepassword.theme.Retro4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ExampleScreen(screenContext: ScreenContext) {
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically), ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Retro1),
            onClick = {
            screenContext.navigation.goToAddPassword()
        }, ) {
            Text(text = "Add Account")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Retro1),
            onClick = {
                GlobalScope.launch {
                    screenContext.sharedState.encodePassword.emit(password.value)
                    withContext(Dispatchers.Main) {
                        screenContext.navigation.goToDisplayPassword()
                    }
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