package app.cloudcoffee.securepassword.example

import app.cloudcoffee.securepassword.framework.FailureCode
import app.cloudcoffee.securepassword.framework.Maybe

class MyUseCase private constructor(
    val name: Maybe<String>,
    val age: Maybe<Int>) {


    companion object {
        fun of(name: String?): MyUseCase {
            return (MyUseCase(Maybe.value(name), Maybe.value(12)))
        }
    }
}

class DomainExample {

    fun testThis() {

        MyUseCase.of("popcorn").name.getOrNull()

    }
}