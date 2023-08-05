package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.screen.ScreenContext
import app.cloudcoffee.securepassword.theme.DiscordBlue
import app.cloudcoffee.securepassword.theme.Purple80

private const val ENCODE_SECRET = "Boulos#187"

@Composable
fun DisplayPassword(screenContext: ScreenContext) {
    val passwordViewModel = viewModel { DisplayPasswordViewModel() }

    val encodeState by screenContext.sharedState.encodePassword.collectAsState()

    val showPasswords = remember { encodeState == ENCODE_SECRET }

    Column(Modifier.verticalScroll(rememberScrollState())) {
        passwordViewModel.passwordState.value?.let { passwordList ->
            passwordList.list.forEach { pointer ->
                //PasswordList item states
                var showMenu by remember { mutableStateOf(false) }

                val model = pointer.value
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showMenu = !showMenu
                    }
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DiscordBlue)
                    .padding(16.dp)) {
                    Column {
                        Text(text = model.title.value, color = Color.White, fontSize = 24.sp)
                        Divider()
                        Text(text = model.username.value,color = Color.White)
                        if(showPasswords)
                            Text(text = model.password.value,color = Color.White)
                        if(model.note.value.isNotBlank())
                            Text(text = model.email.value,color = Color.White)
                        if(model.note.value.isNotBlank())
                            Text(text = model.note.value, color = Color.White)
                        PasswordMenu(showMenu = showMenu, onDelete = {
                            passwordViewModel.deletePassword(pointer)
                            passwordViewModel.updatePasswords()
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordMenu(showMenu: Boolean, onDelete: () -> Unit) {
    var areYouSure by remember { mutableStateOf(false) }
    if(showMenu) {
        Column {
            Button(onClick = {
                if(areYouSure)
                    onDelete()
                areYouSure = true
            }) {
                if(areYouSure)
                    Text(text = "Are you sure you want to delete")
                else
                    Text(text = "Delete")
            }
        }
    }
}