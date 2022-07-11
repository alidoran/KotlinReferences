package alidoran

import alidoran.design_pattern.databinding.ActivityDesignPatternBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import alidoran.design_pattern.java.simple_factory.Main_SimpleFactory
import alidoran.design_pattern.java.factory_method.MainFactoryMethodActivity
import alidoran.design_pattern.java.singleton.SingletonMain
import alidoran.design_pattern.java.builder.MainBuilderActivity
import alidoran.design_pattern.java.prototype.MainPrototype
import alidoran.design_pattern.java.adapter.AdapterMain
import alidoran.design_pattern.java.memento.MementoActivity
import alidoran.design_pattern.java.state.StateActivity


class DesignPatternActivity : AppCompatActivity() {
    lateinit var binding: ActivityDesignPatternBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignPatternBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() {

        binding.btnSimpleFactory.setOnClickListener {
            startActivity(Intent(this, Main_SimpleFactory::class.java))
        }

        binding.btnFactoryMethod.setOnClickListener {
            startActivity(Intent(this, MainFactoryMethodActivity::class.java))
        }

        binding.btnSingleton.setOnClickListener {
            startActivity(Intent(this, SingletonMain::class.java))
        }

        binding.btnBuilder.setOnClickListener {
            startActivity(Intent(this, MainBuilderActivity::class.java))
        }

        binding.btnPrototype.setOnClickListener {
            startActivity(Intent(this, MainPrototype::class.java))
        }

        binding.btnAdapter.setOnClickListener {
            startActivity(Intent(this, AdapterMain::class.java))
        }

        binding.btnMemento.setOnClickListener {
            startActivity(Intent(this, MementoActivity::class.java))
        }

        binding.btnState.setOnClickListener{
            startActivity(Intent(this, StateActivity::class.java))
        }

    }
}