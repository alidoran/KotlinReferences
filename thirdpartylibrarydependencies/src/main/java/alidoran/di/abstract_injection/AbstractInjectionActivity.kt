package alidoran.di.abstract_injection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.R
import javax.inject.Inject

class AbstractInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var car: CarAbstractInject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abstract_injection)

        DaggerCarAbstractInjectionComponent.create().inject(this)

        car.start()
    }
}