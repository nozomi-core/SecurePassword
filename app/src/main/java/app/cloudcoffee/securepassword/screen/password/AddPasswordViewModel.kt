package app.cloudcoffee.securepassword.screen.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import app.cloudcoffee.securepassword.client.data.password.UnencryptedPassword
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddPasswordViewModel: ViewModel(), KoinComponent {

    private val passwordApi: PasswordApi by inject()

    fun addPassword(username: String,
                    password: String) {

        val unencryptedPassword = UnencryptedPassword.of(
            username = username,
            password = password)

        viewModelScope.launch {
            passwordApi.insert(unencryptedPassword)
        }
    }
}