package app.cloudcoffee.securepassword.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cloudcoffee.securepassword._example.theme.Purple80

@Composable
@Preview
fun DisplayPassword() {
    val passwordViewModel = viewModel { DisplayPasswordViewModel() }

    Column(Modifier.verticalScroll(rememberScrollState())) {
        passwordViewModel.passwordState.value?.let { passwordList ->
            passwordList.list.forEach { pointer ->
                val model = pointer.value
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Purple80)
                    .padding(16.dp)) {
                    Column {
                        Text(text = model.title.value)
                        Text(text = model.username.value)
                        Text(text = model.password.value)
                        if(model.note.value.isNotBlank())
                            Text(text = model.email.value)
                        if(model.note.value.isNotBlank())
                            Text(text = model.note.value)
                    }
                }
            }
        }
    }
}