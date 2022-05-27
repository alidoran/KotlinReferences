package ir.alidoran.teach_kotlin

import android.util.Log
import kotlin.random.Random

class CollectionVersusSequences {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = listOf(8, 7, 5, 2)
            fibonacciByYield()

        }

        private fun callCollectionSimpleForm(list: List<Int>) {
            list.map { it + 1 }.filter { it % 2 == 0 }.maxByOrNull { it }
        }

        private fun callSequenceSimpleForm(list: List<Int>) {
            list.map { it + 1 }.filter { it % 2 == 0 }.maxByOrNull { it }
        }

        //region Different running
        private fun firstMethod(i: Int): Int {
            println("$i")
            return (i)
        }

        private fun secondMethod(i: Int): Boolean {
            println("$i")
            return i % 2 == 0
        }


        private fun collectionCall(list: List<Int>) {
            println("collection Start")
            val collection = list.map { firstMethod(it) }
                .filter { secondMethod(it) }  //Result is : 8-7-5-2-8-7-5-2 and order is horizontal
            println("collection End \n")
            println("sequence Start")
            val sequence = list.asSequence().map { firstMethod(it) }.filter { secondMethod(it) }
                .toList()  //Result is : 8-8-7-7-5-5-2-2 and order is vertical
            println("sequence End \n")
            println("sequenceNotRun Start")
            val sequenceNotRun = list.asSequence().map { firstMethod(it) }
                .filter { secondMethod(it) }  //result is empty
            println("sequenceNotRun End \n")
        }
        //endregion

        private fun generatorSequence() {
            val sequenceList =
                generateSequence { Random.nextInt(100).takeIf { it > 5 } } //list by condition
            val sequenceStringList =
                generateSequence { readLine().takeIf { it != "exit" } } //list by condition
            val dedicatedList =
                generateSequence(3) { (it + 1).takeIf { it < 8 } } //Answer is {3,4,5,6,7}
            val result = sequenceList.toString()  //Code not generate since want a result
        }

        private fun sequenceByLazyConcept() {
            //Code not generated to the asked about the answer
            val dedicatedList =
                generateSequence(3) { (it + 1) } //This line answer is overhead but the code line won't run till to the next line.
            dedicatedList.take(5).toList() //The code run here by a specific answer
        }

        private fun sequenceRunningRules() {
            val sequence = generateSequence(3) { n ->
                println("Code run")
                (n + 1).takeIf { it < 7 }
            }
            println("The first answer is \n ${sequence.first()}")  //"code run" not print
            println("The second answer is \n ${sequence.toList()}") //"code run" will print four time
        }

        private fun yieldSequence() {
            val sequence = sequence {
                var x = 0
                while (true) {
                    yield(x++)
                }
            }
            val answer = sequence.take(4).toList() //[0,1,2,3]
        }

        private fun yieldAndLambda() {
            val mySequence = sequence {
                var x = 1
                while (true)
                    yield(x++)
            }
            val answer = mySequence.map { it * it }.take(3).toList()
        }

        private fun yieldAll() {
            val mySequence = sequence {
                var x = 1
                while (true)
                    yieldAll(2..4)
            }
            val answer = mySequence.map { it * it }.take(5).toList() //answer is [4,9,16,4,9]
        }

        private fun fibonacciByYield() {
            val mySequence = sequence {
                var x = 1
                var y = 1
                while (true) {
                    yield(x)
                    y += x
                    x =y - x
                }
            }
            val answer = mySequence.take(10).toList()
        }
    }
}