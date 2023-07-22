package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DisplayPassword() {
    val passwordViewModel = viewModel { DisplayPasswordViewModel() }

    Column {
        passwordViewModel.passwordState.value?.let { passwordList ->
            passwordList.list.forEach {
                Text(text = it.username.value)
            }
        }
    }
}