package app.cloudcoffee.securepassword.screen.password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import app.cloudcoffee.securepassword.client.data.password.UnencryptedPassword
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddPasswordViewModel: ViewModel(), KoinComponent {

    private val passwordApi: PasswordApi by inject()

    private val testModel = MutableLiveData<String>()

    fun addPassword(title: String,
                    username: String,
                    password: String,
                    email: String,
                    note: String) {

        val unencryptedPassword = UnencryptedPassword.of(
            title = title,
            username = username,
            password = password,
            email = email,
            note = note)

        viewModelScope.launch {
            passwordApi.insert(unencryptedPassword)
        }
    }
}