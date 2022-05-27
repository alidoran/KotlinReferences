package ir.alidoran.teach_kotlin

class BoundingReference {

    fun nonBoundReference() {
        class Person(val name: String, val age: Int) {
            fun isOlder(ageLimit: Int) = age > ageLimit
        }

        val agePredicate1 : (Person, Int) -> Boolean = { person, ageLimit -> person.isOlder(ageLimit)}
        val agePredicate2 : (Person , Int) -> Boolean = Person::isOlder
        val agePredicateFinal = Person::isOlder

        val alice = Person("Alice" , 29)
        agePredicateFinal(alice , 21) // true
    }

    fun boundReference() {
        class Person(val name: String, val age: Int) {
            fun isOlder(ageLimit: Int) = age > ageLimit
        }

        val alice = Person("Alice" , 29)
        val agePredicate1 : (Int) -> Boolean = alice::isOlder
        val agePredicate2 : (Int) -> Boolean = {ageLimit -> alice.isOlder(ageLimit)}
        val agePredicateFinal = alice::isOlder
        agePredicateFinal(21) // true
    }

    fun functionTypeVsFunctionReference() {
        class Person(val name: String, val age: Int) {
            fun isOlderMethod(ageLimit: Int) = age > ageLimit
            var isOlderFunctionType = { ageLimit: Int -> age > ageLimit}
        }

        val agePredicate = Person::isOlderMethod
        val alice = Person("Alice" , 29)

        agePredicate(alice , 21) // true
        alice.isOlderFunctionType(21) //true
    }

    fun boundOnThisReference(){
        class Person(val name: String, val age: Int) {
            fun isOlder(ageLimit: Int) = age > ageLimit
            fun getAgePredicate() = ::isOlder
        }

        val alice = Person("Alice" , 29)
        val predicate = alice.getAgePredicate()
    }

}