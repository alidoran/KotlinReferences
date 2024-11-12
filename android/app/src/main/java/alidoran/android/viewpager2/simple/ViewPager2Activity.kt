package alidoran.android.viewpager2.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityViewPager2Binding

class ViewPager2Activity : AppCompatActivity() {

    lateinit var binding: ActivityViewPager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}