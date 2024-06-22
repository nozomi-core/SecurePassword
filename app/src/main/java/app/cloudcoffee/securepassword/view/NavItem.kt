package app.cloudcoffee.securepassword.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavItem(name: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = name)
    }
}