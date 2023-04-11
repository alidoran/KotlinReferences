package alidoran.android.mvp

import alidoran.android.databinding.ActivityMvpBinding
import alidoran.android.mvp.base_mvp.view.MvpByBaseActivity
import alidoran.android.mvp.simple_mvp.view.SimpleMvpActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MvpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpleMvp.setOnClickListener {
            startActivity(Intent(this, SimpleMvpActivity::class.java))
        }

        binding.btnBaseMvp.setOnClickListener{
            startActivity(Intent(this, MvpByBaseActivity::class.java))
        }
    }
}