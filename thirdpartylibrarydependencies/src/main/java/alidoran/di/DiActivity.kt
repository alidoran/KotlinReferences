package alidoran.di

import alidoran.di.retrofit.abstract_injection.AbstractInjectionActivity
import alidoran.di.retrofit.constructor.ConstructorInjectActivity
import alidoran.di.retrofit.feild_injected_class.FieldInjectedClassActivity
import alidoran.di.retrofit.field.FieldInjectionActivity
import alidoran.di.retrofit.interface_injection.InterfaceInjectionActivity
import alidoran.di.retrofit.method_injection.MethodInjectionActivity
import alidoran.di.simple_di.SimpleDiActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alidoran.thirdpartylibrarydependencies.R
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityDiBinding

class DiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimple.setOnClickListener {
            val intent = Intent(this, SimpleDiActivity::class.java)
            startActivity(intent)
        }

        binding.btnConstructor.setOnClickListener {
            val intent = Intent(this, ConstructorInjectActivity::class.java)
            startActivity(intent)
        }

        binding.btnField.setOnClickListener {
            val intent = Intent(this, FieldInjectionActivity::class.java)
            startActivity(intent)
        }

        binding.btnInterface.setOnClickListener {
            val intent = Intent(this, InterfaceInjectionActivity::class.java)
            startActivity(intent)
        }

        binding.btnMethod.setOnClickListener {
            val intent = Intent(this, MethodInjectionActivity::class.java)
            startActivity(intent)
        }

        binding.btnFieldInjectedClass.setOnClickListener {
            val intent = Intent(this, FieldInjectedClassActivity::class.java)
            startActivity(intent)
        }

        binding.btnFieldInjectedClass.setOnClickListener {
            val intent = Intent(this, FieldInjectedClassActivity::class.java)
            startActivity(intent)
        }

        binding.btnAbstract.setOnClickListener {
            val intent = Intent(this, AbstractInjectionActivity::class.java)
            startActivity(intent)
        }
    }
}