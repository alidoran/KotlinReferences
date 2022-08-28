package alidoran.kotlin_base

//region constructor method
class SourceClassConstructor {
    fun call(){
        DestinationClassConstructor(object : ConstructorInterface {
            override fun onClick(number: Int) {
                println("The chosen number is $number")
            }
        })
    }
}

class DestinationClassConstructor(
    private val myConstructor: ConstructorInterface
) {
    init {
        onClick()
    }
    private fun onClick(){
        myConstructor.onClick(5)
    }
}

interface ConstructorInterface {
    fun onClick(number: Int)
}
//endregion

//region inheritance/Method method
class SourceClassInheritance: InheritanceInterface{
    init {
        DestinationClassInheritance().setOnClickInheritance(this)
    }

    override fun onClick(number: Int) {
        println("The chosen number is $number")
    }

}

class DestinationClassInheritance{
    private var myInterface: InheritanceInterface? = null

    fun setOnClickInheritance(inheritance: InheritanceInterface){
        myInterface = inheritance
    }

    fun onclick(){
        myInterface!!.onClick(5)
    }


}

interface InheritanceInterface {
    fun onClick(number: Int)
}
//endregion

//region InterfaceLess

class SourceClassNoInterface{
    fun call(){
        DestinationClassNoInterface().setOnItemClickListener {
            println("The chosen number is $it")
        }
    }
}

class DestinationClassNoInterface{
    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
    fun itemClicked(){
        onItemClickListener?.let {
            it(5)
        }
    }
}

//endregion