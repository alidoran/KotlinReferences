package alidoran.android.binding

import alidoran.android.databinding.ActivityDataBindingBinding
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter

/*
add to gradle
android{
    buildFeatures {
        dataBinding true
    }
}
 */
class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDataBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.model = DataBindingModel(
            "Hello data binding first line",
            "Hello data binding second line"
        )
    }
/*
    plugins {
        id 'java-kapt'
    }

 */
    companion object{
        @JvmStatic
        @BindingAdapter("bind:setSubjectText")
        fun setSubjectText(textView: TextView, string: String){
            val mTxt = "Subject: ${string}"
            textView.text = mTxt
        }
    }

}