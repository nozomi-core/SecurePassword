package app.cloudcoffee.securepassword.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.cloudcoffee.securepassword.screen.example.NavigationScreen
import app.cloudcoffee.securepassword.screen.password.AddPasswordScreen
import app.cloudcoffee.securepassword.screen.password.DisplayPassword
import app.cloudcoffee.securepassword.screen.password.PasswordScreen

fun initExampleApplication(
    builder: NavGraphBuilder,
    controller: NavController
) {
    val screenContext = ScreenContext(
        UINavigation(controller),
        SharedAppState()
    )
    builder.buildExampleRoutes(screenContext)
}

fun NavGraphBuilder.buildExampleRoutes(screenContext: ScreenContext) {
    composable(UINavigation.NAVIGATION_SCREEN) { NavigationScreen(screenContext) }
    composable(UINavigation.PASSWORD_SCREEN) { PasswordScreen(screenContext) }
    composable(UINavigation.ADD_PASSWORD_SCREEN) { AddPasswordScreen(screenContext) }
    composable(UINavigation.DISPLAY_PASSWORD) { DisplayPassword(screenContext) }
}

class UINavigation(val controller: NavController) {
    fun goToPasswordScreen() = controller.navigate(PASSWORD_SCREEN)
    fun goToAddPassword() = controller.navigate(ADD_PASSWORD_SCREEN)
    fun goToDisplayPassword() = controller.navigate(DISPLAY_PASSWORD)

    companion object {
        const val PASSWORD_SCREEN = "password-screen"
        const val NAVIGATION_SCREEN = "example-screen"
        const val ADD_PASSWORD_SCREEN = "add-password-screen"
        const val DISPLAY_PASSWORD = "display-password"
    }
}