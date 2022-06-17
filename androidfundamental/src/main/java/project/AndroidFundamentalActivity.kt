package project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import alidoran.android_fundamental.databinding.ActivityAndroidFundamentalBinding
import project.Backlight_light_change.BackLightChangeActivity
import project.binding.DataBindingActivity
import project.binding.ViewBindingActivity
import project.bottom_sheet.BottomSheetActivity
import project.lifecycle.LifeCycleActivity
import project.mvvm_livedata.view.MvvmMainActivity
import project.recycler_view.RecyclerActivity
import project.room.activity.RoomActivity
import project.services.ServicesActivity
import project.shared_preference.SharedPreferenceActivity
import project.view_to_model.ViewToModelActivity

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
            project.Intents().sendEMail("Hello", "alidoran@gmail.com", "Hello World")
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

        binding.btnRoom.setOnClickListener{
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewToModel.setOnClickListener{
            val intent = Intent(this, ViewToModelActivity::class.java)
            startActivity(intent)
        }

    }



}