package alidoran.di.retrofit.field

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityFieldInjectionBinding
import javax.inject.Inject

class FieldInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var car:CarField

    @Inject
    lateinit var car2:CarField

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityFieldInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carComponentField = DaggerCarComponentField.create()
        carComponentField.inject(this)

        car.start()
        car2.start()
    }
}