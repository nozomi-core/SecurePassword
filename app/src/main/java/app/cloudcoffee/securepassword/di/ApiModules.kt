package app.cloudcoffee.securepassword.di

import app.cloudcoffee.securepassword.client.data.password.FirebasePasswordApi
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import org.koin.dsl.module

val API_MODULES = module {
    single<PasswordApi> { FirebasePasswordApi() }
}