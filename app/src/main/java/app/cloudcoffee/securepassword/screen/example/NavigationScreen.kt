package app.cloudcoffee.securepassword.screen.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.cloudcoffee.securepassword.R
import app.cloudcoffee.securepassword.screen.ScreenContext
import app.cloudcoffee.securepassword.view.NavItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NavigationScreen(screenContext: ScreenContext) {
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        NavItem(
            name = stringResource(id = R.string.add_account),
            onClick = {
                screenContext.navigation.goToAddPassword()
            }
        )
        NavItem(
            name = stringResource(id = R.string.display),
            onClick = {
                GlobalScope.launch {
                    screenContext.sharedState.encodePassword.emit(password.value)
                    withContext(Dispatchers.Main) {
                        screenContext.navigation.goToDisplayPassword()
                    }
                }
            }
        )
        BasicTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            }
        )
    }
}