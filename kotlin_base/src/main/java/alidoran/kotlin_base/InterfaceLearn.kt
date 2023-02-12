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

        private fun getName(nameId : Int): String{
            val result:String =
                when(nameId) {
                1 -> "FirstName"
                2 -> "SecondName"
                else-> "ThirdName"
            }
            return result
        }
    }
}
