package alidoran.third_party.rx_java

import io.reactivex.Observable

class AnimalSample {
    val animal = arrayListOf("Tiger", "Lion", "Elephant")

    fun simpleObservable() {
        Observable.just("Hello World")
            .subscribe {
                    value -> println(value)
            }
    }

    fun listObservable(){
        Observable.just("Apple", "Orange", "Banana")
            .subscribe(
                { value -> println("Received: $value") },
                { error -> println("Error: $error") },   // onNext
                { println("Completed!") }                 // onComplete
            )
    }

    fun listObservableError(){
        Observable.just("Apple", "Orange", "Banana")
            .map({ input -> throw RuntimeException() } )
            .subscribe(
                { value -> println("Received: $value") }, // onNext
                { error -> println("Error: Handle Error = $error") },    // onError
                { println("Completed!") }                 // onComplete
            )
    }
}

fun main() {
    AnimalSample().listObservableError()
}