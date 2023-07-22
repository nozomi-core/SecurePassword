package app.cloudcoffee.securepassword.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.cloudcoffee.securepassword.screen.example.ExampleScreen
import app.cloudcoffee.securepassword.screen.login.LoginScreen
import app.cloudcoffee.securepassword.screen.password.AddPasswordScreen
import app.cloudcoffee.securepassword.screen.password.DisplayPassword
import app.cloudcoffee.securepassword.screen.password.PasswordScreen

private val LOGIN_SCREEN = "login-screen"
private val PASSWORD_SCREEN = "password-screen"
private val EXAMPLE_SCREEN = "example-screen"
private val ADD_PASSWORD_SCREEN = "add-password-screen"
private val DISPLAY_PASSWORD = "display-password"

fun initExampleApplication(builder: NavGraphBuilder,
                        controller: NavController) {
    val screenContext = ScreenContext(UINavigation(controller))
    builder.buildExampleRoutes(screenContext)
}

fun NavGraphBuilder.buildExampleRoutes(screenContext: ScreenContext) {
    composable(EXAMPLE_SCREEN) { ExampleScreen(screenContext) }
    composable(LOGIN_SCREEN) { LoginScreen(screenContext) }
    composable(PASSWORD_SCREEN) { PasswordScreen(screenContext) }
    composable(ADD_PASSWORD_SCREEN) { AddPasswordScreen(screenContext) }
    composable(DISPLAY_PASSWORD) { DisplayPassword() }
}

class UINavigation(val controller: NavController) {
    fun goToPasswordScreen() = controller.navigate(PASSWORD_SCREEN)
    fun goToLogin() = controller.navigate(LOGIN_SCREEN)
    fun goToAddPassword() = controller.navigate(ADD_PASSWORD_SCREEN)
    fun goToDisplayPassword() = controller.navigate(DISPLAY_PASSWORD)

    companion object {
        val START_NAVIGATION = EXAMPLE_SCREEN
    }
}