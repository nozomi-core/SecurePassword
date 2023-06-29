package app.cloudcoffee.securepassword._env

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

object Env {

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext
}