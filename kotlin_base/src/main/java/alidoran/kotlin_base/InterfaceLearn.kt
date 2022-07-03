package alidoran.kotlin_base

class InterfaceLearn{
    interface InterfaceTwoFun{
        fun firstAttribute(intOne: Int) : String
        fun secondAttribute(intTwo : Int) : String
    }

    interface SimpleInterface {
        val interfaceName: String
    }

    interface SecondInterface{
        val simpleInterface: SimpleInterface
    }

    class UseSimpleInterface(nameId: Int) : SimpleInterface {
        override val interfaceName = getName(nameId)

        fun getName(nameId : Int): String{
            var result:String
            when(nameId) {
                1 -> result= "FirstName"
                2 -> result= "SecondName"
                else-> result= "ThirdName"
            }
            return result
        }
    }
}
