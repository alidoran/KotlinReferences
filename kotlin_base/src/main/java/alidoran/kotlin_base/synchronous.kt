package alidoran.kotlin_base

interface SampleInterface {
    fun onSuccess(): Boolean
    fun onFailure()
}

fun callBackCaller(sampleInterface: SampleInterface){
    if (!sampleInterface.onSuccess())
        sampleInterface.onFailure()
}

fun callBackImplementer(){
    callBackCaller(object : SampleInterface{
        override fun onSuccess(): Boolean {
            return false
        }

        override fun onFailure() {
            println("This faced by failure")
        }

    })
}

fun main() {
    callBackImplementer()
}