package alidoran.third_party.di.dagger.abstract_injection

import alidoran.third_party.R
import alidoran.third_party.di.abstract_injection.DaggerCarAbstractInjectionComponent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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