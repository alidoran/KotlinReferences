package android.test.junit5

class UserJunit(var id: Int, private val firstName: String, private val lastName: String) {

    var reputation: Int = 0

    fun changeReputation(amount: Int) {
        this.id = amount
    }

    fun isFillParameters(): Boolean{
        if (this.id == 0 || firstName == "" || lastName == "") throw IllegalArgumentException("Id or Name is null")
        return true
    }

    fun nameToLowercase(): String{
        return firstName.lowercase()
    }

    fun concatNameAndFamily(): String{
        return firstName + lastName
    }

}