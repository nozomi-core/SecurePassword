package app.cloudcoffee.securepassword.screen.password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import app.cloudcoffee.securepassword.client.data.password.PasswordList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DisplayPasswordViewModel: ViewModel(), KoinComponent {

    private val passwordApi: PasswordApi by inject()

    private val _passwordState = mutableStateOf<PasswordList?>(null)
    val passwordState: State<PasswordList?> get() = _passwordState

    init {
        viewModelScope.launch {
            val result = passwordApi.getAllPasswords()

            withContext(Dispatchers.Main) {
                _passwordState.value = result.getOrNull()
            }
        }
    }
}