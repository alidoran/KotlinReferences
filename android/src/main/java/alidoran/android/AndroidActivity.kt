package alidoran.android

import alidoran.android.Backlight_light_change.BackLightChangeActivity
import alidoran.android.binding.DataBindingActivity
import alidoran.android.binding.ViewBindingActivity
import alidoran.android.bottom_sheet.BottomSheetActivity
import alidoran.android.databinding.ActivityAndroidBinding
import alidoran.android.lifecycle.LifeCycleActivity
import alidoran.android.mvp.view.MvpActivity
import alidoran.android.mvvm_livedata.view.MvvmMainActivity
import alidoran.android.paging_view.PagingRecyclerActivity
import alidoran.android.recycler_view.RecyclerActivity
import alidoran.android.room.activity.RoomActivity
import alidoran.android.services.ServicesActivity
import alidoran.android.shared_preference.SharedPreferenceActivity
import alidoran.android.socket.SocketActivity
import alidoran.android.view_to_model.ViewToModelActivity
import alidoran.android.viewpager.ViewPagerSliderActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AndroidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidBinding.inflate(layoutInflater)
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
            alidoran.android.Intents().sendEMail("Hello", "alidoran@gmail.com", "Hello World")
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

        binding.btnSocket.setOnClickListener{
            val intent = Intent(this, SocketActivity::class.java)
            startActivity(intent)
        }

        binding.btnMvp.setOnClickListener{
            val intent = Intent(this, MvpActivity::class.java)
            startActivity(intent)
        }

        binding.btnPaging.setOnClickListener{
            val intent = Intent(this, PagingRecyclerActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewpagerSlider.setOnClickListener{
            val intent = Intent(this, ViewPagerSliderActivity::class.java)
            startActivity(intent)
        }
    }
}