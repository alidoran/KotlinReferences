package alidoran.third_party.di.dagger.singleton_injection

import alidoran.third_party.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class SingletonActivity : AppCompatActivity() {

    @Inject
    lateinit var car1: CarSingleton
    @Inject
    lateinit var car2: CarSingleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleton)

        DaggerCarSingletonComponent.create().inject(this)

        //these are the same
        println("car1: $car1")
        println("car2: $car2")


        val car3 = DaggerCarSingletonComponent.create().getCar()
        val car4 = DaggerCarSingletonComponent.create().getCar()

        //these are not the same
        println("car3: $car3")
        println("car4: $car4")
    }
}