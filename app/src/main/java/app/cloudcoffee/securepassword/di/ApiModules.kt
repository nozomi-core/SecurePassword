package app.cloudcoffee.securepassword.di

import app.cloudcoffee.securepassword.client.DatabaseClient
import app.cloudcoffee.securepassword.client.FirebaseDatabaseClient
import app.cloudcoffee.securepassword.client.data.password.FirebasePasswordApi
import app.cloudcoffee.securepassword.client.data.password.PasswordApi
import org.koin.dsl.module

val CLIENT_MODULES = module {
    single<DatabaseClient> { FirebaseDatabaseClient() }
}

val API_MODULES = module {
    single<PasswordApi> { FirebasePasswordApi() }
}