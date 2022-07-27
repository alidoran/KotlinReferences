package alidoran.third_party.di.dagger.interface_injection

import alidoran.third_party.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class InterfaceInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var car: CarInterfaceInject
    @Inject
    lateinit var carMulti: CarInterfaceInjectMulti

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface_injection)

        val carInterfaceInject = DaggerCarInterfaceInjectionComponent.create()
        carInterfaceInject.inject(this)

        car.start()


        /*
        multi input
         */

        val carInterfaceInjectMulti = DaggerMultiInterfaceInjectionComponent.create()
        carInterfaceInjectMulti.inject(this)

        carMulti.start()
    }
}