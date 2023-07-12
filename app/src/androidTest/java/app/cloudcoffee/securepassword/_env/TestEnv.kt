package app.cloudcoffee.securepassword._env

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

object TestEnv {
    val password = "MyPassword#787"

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext
}