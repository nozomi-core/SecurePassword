package app.cloudcoffee.securepassword.client.data.password

import app.cloudcoffee.securepassword.framework.What

data class Email(override val value: String): What<String>
data class Username(override val value: String): What<String>
data class Password(override val value: String): What<String>
data class Note(override val value: String): What<String>