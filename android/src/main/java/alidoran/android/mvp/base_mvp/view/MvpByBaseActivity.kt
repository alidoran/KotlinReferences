package alidoran.android.mvp.base_mvp.view

import alidoran.android.databinding.ActivityMvpByBaseBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MvpByBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvpByBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpByBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}