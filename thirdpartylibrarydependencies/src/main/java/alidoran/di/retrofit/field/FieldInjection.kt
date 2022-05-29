package alidoran.di.retrofit.field

import android.widget.Toast
import dagger.Component
import javax.inject.Inject

class WheelField @Inject constructor(){
    fun getTireType(): String {
        return "Interface injection tire"
    }
}

class CarField @Inject constructor(inputWheel: WheelField){
    var wheel: WheelField
    init {
        wheel = inputWheel
    }

    fun start(){
        println("Field inject car started by ${wheel.getTireType()}")
    }
}

@Component
interface CarComponentField{
    fun inject(fieldInjectionActivity: FieldInjectionActivity)
}