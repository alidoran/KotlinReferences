package alidoran.third_party.di.field


import alidoran.third_party.databinding.ActivityFieldInjectionBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class FieldInjectionActivity : AppCompatActivity() {

    @Inject
    lateinit var car: CarField

    @Inject
    lateinit var car2: CarField

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