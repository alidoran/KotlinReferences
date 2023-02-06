package alidoran.kotlin_base

class VolatileLearn {

    companion object {
        private var counterWithoutVolatile = 0
        @Volatile
        private var counterWithVolatile = 0
    }

    fun withoutVolatile() {
        val t1 = Thread {
            var localCounter = counterWithoutVolatile

            while (localCounter < 10) {
                if (localCounter != counterWithoutVolatile) {
                    println("T1 local counter is changed")
                    localCounter = counterWithoutVolatile
                }
            }
        }

        val t2 = Thread {
            var localCounter = counterWithoutVolatile

            while (localCounter < 10) {
                println("T2 increase counter to ${localCounter + 1}")
                counterWithoutVolatile = localCounter++

                try {
                    Thread.sleep(500)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }

        t1.start()
        t2.start()
    }

    fun withVolatile() {
        val t1 = Thread {
            var localCounter = counterWithVolatile

            while (localCounter < 10) {
                if (localCounter != counterWithVolatile) {
                    println("T1 local counter is changed")
                    localCounter = counterWithVolatile
                }
            }
        }

        val t2 = Thread {
            var localCounter = counterWithVolatile

            while (localCounter < 10) {
                println("T2 increase counter to ${localCounter + 1}")
                counterWithVolatile = localCounter++

                try {
                    Thread.sleep(500)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }

        t1.start()
        t2.start()
    }
}

fun main() {
//    VolatileLearn().withVolatile()
    VolatileLearn().withoutVolatile()
}