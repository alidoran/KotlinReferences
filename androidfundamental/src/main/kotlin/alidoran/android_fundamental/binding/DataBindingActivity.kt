package alidoran.android_fundamental.binding

import alidoran.android_fundamental.databinding.ActivityDataBindingBinding.inflate
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
        val binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.model = DataBindingModel(
            "Hello data binding first line",
            "Hello data binding second line"
        )
    }
/*
    plugins {
        id 'kotlin-kapt'
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