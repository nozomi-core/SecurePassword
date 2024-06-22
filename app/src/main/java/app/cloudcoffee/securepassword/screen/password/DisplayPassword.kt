package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword.client.data.password.UnencryptedPassword
import app.cloudcoffee.securepassword.screen.ScreenContext
import app.cloudcoffee.securepassword.theme.AppColor

private const val ENCODE_SECRET = "Boulos#187"

@Composable
fun DisplayPassword(screenContext: ScreenContext) {
    val passwordViewModel = viewModel { DisplayPasswordViewModel() }
    val encodeState by screenContext.sharedState.encodePassword.collectAsState()
    val showPasswords = remember { encodeState == ENCODE_SECRET }

    Column(
        Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(2.dp)) {
        passwordViewModel.passwordState.value?.let { passwordList ->
            passwordList.list.forEach { pointer ->
               PasswordItem(item = pointer.value,
                   showPassword = showPasswords
               )
            }
        }
    }
}

@Composable
fun PasswordItem(item: UnencryptedPassword, showPassword: Boolean) {
    Column(Modifier
        .fillMaxWidth()
        .background(AppColor.Retro1)
        .padding(16.dp)
    ) {
        Text(text = item.title.value, color = Color.White , fontSize = 21.sp)
        Text(text = item.username.value, color = Color.White)
        if(showPassword) {
            Text(text = item.password.value, color = Color.White)
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