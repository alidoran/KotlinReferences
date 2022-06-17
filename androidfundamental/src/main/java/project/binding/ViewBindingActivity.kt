package project.binding

import alidoran.android_fundamental.databinding.ActivityViewBindingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


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