package alidoran.android_fundamental

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import alidoran.android_fundamental.Backlight_light_change.BackLightChangeActivity
import alidoran.android_fundamental.binding.DataBindingActivity
import alidoran.android_fundamental.binding.ViewBindingActivity
import alidoran.android_fundamental.bottom_sheet.BottomSheetActivity
import alidoran.android_fundamental.databinding.ActivityAndroidFundamentalBinding
import alidoran.android_fundamental.lifecycle.LifeCycleActivity
import alidoran.android_fundamental.mvvm_livedata.view.MvvmMainActivity
import alidoran.android_fundamental.recycler_view.RecyclerActivity
import alidoran.android_fundamental.services.ServicesActivity
import alidoran.android_fundamental.shared_preference.SharedPreferenceActivity

class AndroidFundamentalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidFundamentalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMainRecycler.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        binding.btnLifecycle.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity::class.java)
            startActivity(intent)
        }

        binding.btnBacklightChangeLight.setOnClickListener {
            val intent = Intent(this, BackLightChangeActivity::class.java)
            startActivity(intent)
        }

        binding.btnBottomSheetMain.setOnClickListener {
            val intent = Intent(this, BottomSheetActivity::class.java)
            startActivity(intent)
        }

        binding.btnSendEmail.setOnClickListener {
            Intents().sendEMail("Hello", "alidoran@gmail.com", "Hello World")
        }

        binding.btnServices.setOnClickListener{
            val intent = Intent(this, ServicesActivity::class.java)
            startActivity(intent)
        }

        binding.btnSharedPreferences.setOnClickListener{
            val intent = Intent(this, SharedPreferenceActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewBinding.setOnClickListener{
            val intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }

        binding.btnDataBinding.setOnClickListener{
            val intent = Intent(this, DataBindingActivity::class.java)
            startActivity(intent)
        }

        binding.btnMvvmLiveData.setOnClickListener{
            val intent = Intent(this, MvvmMainActivity::class.java)
            startActivity(intent)
        }

    }



}