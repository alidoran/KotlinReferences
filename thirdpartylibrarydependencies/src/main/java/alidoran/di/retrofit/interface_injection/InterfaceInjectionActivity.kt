package alidoran.di.retrofit.interface_injection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.R
import javax.inject.Inject

class InterfaceInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var car: CarInterfaceInject

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface_injection)

        val carInterfaceInject = DaggerCarInterfaceInjectionComponent.create()
        carInterfaceInject.inject(this)

        car.start()
    }
}