package alidoran.di.retrofit.field_injected_class

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.R

class FieldInjectedClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field_injected_class)

        val car = DaggerCarFieldInjectedClassComponent.create().inject()
        car.start()
    }
}