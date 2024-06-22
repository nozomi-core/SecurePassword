package app.cloudcoffee.securepassword.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class SecureThemeData(val main: Color)

val LocalTheme = compositionLocalOf { SecureThemeData(AppColor.Retro1) }

@Composable
fun SecureTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider() {
        content()
    }
}