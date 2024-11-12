package alidoran.android.binding

import alidoran.android.databinding.ActivityViewBindingBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/*
add to gradle
android{
    buildFeatures {
        viewBinding true
    }
}
 */
class ViewBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mTxt = "Hi View Binding"
        binding.txtViewBinding.text = mTxt
    }
}