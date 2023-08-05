package app.cloudcoffee.securepassword.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SharedAppState: ViewModel() {

    val encodePassword = MutableStateFlow("")
}