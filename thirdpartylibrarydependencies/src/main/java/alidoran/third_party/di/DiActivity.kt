package alidoran.third_party.di

import alidoran.third_party.databinding.ActivityDiBinding
import alidoran.third_party.di.dagger.abstract_injection.AbstractInjectionActivity
import alidoran.third_party.di.dagger.constructor.ConstructorInjectActivity
import alidoran.third_party.di.dagger.field.FieldInjectionActivity
import alidoran.third_party.di.dagger.field_injected_class.FieldInjectedClassActivity
import alidoran.third_party.di.dagger.interface_injection.InterfaceInjectionActivity
import alidoran.third_party.di.dagger.method_injection.MethodInjectionActivity
import alidoran.third_party.di.dagger.simple_di.SimpleDiActivity
import alidoran.third_party.di.dagger.singleton_injection.SingletonActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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

        binding.btnSingleton.setOnClickListener {
            val intent = Intent(this, SingletonActivity::class.java)
            startActivity(intent)
        }
    }
}