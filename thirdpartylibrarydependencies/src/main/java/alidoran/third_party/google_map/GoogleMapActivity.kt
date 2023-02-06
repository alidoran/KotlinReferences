package alidoran.third_party.google_map

import alidoran.third_party.databinding.ActivityGoogleMapBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GoogleMapActivity : AppCompatActivity() {
    lateinit var binding: ActivityGoogleMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = MapsFragment()
        supportFragmentManager.beginTransaction().add(binding.mapFragment.id,fragment).commit()
    }
}