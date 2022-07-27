package alidoran.third_party.di.dagger.field_injected_class


import alidoran.third_party.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FieldInjectedClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_injected_class)

        val car = DaggerCarFieldInjectedClassComponent.create().inject()
        car.start()
    }
}