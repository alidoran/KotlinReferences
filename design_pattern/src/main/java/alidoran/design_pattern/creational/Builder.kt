package alidoran.design_pattern.creational

class User private constructor(
    val name: String,
    val age: Int?,
    val email: String?
) {
    class Builder {
        private var name: String = ""
        private var age: Int? = null
        private var email: String? = null

        fun setName(name: String) = apply { this.name = name }
        fun setAge(age: Int) = apply { this.age = age }
        fun setEmail(email: String) = apply { this.email = email }

        fun build(): User = User(name = name, age = age, email = email)
    }
}


val user = User.Builder()
    .setName("AliDoran")
    .setAge(40)
    .setEmail("alidoran@gmail.com")
    .build()

fun main() {
    val name = user.name
}