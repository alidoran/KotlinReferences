package android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.Backlight_light_change.BackLightChangeActivity
import android.binding.DataBindingActivity
import android.binding.ViewBindingActivity
import android.bottom_sheet.BottomSheetActivity
import android.lifecycle.LifeCycleActivity
import android.mvp.view.MvpActivity
import android.mvvm_livedata.view.MvvmMainActivity
import android.paging_view.PagingRecyclerActivity
import android.recycler_view.RecyclerActivity
import android.room.activity.RoomActivity
import android.services.ServicesActivity
import android.shared_preference.SharedPreferenceActivity
import android.socket.SocketActivity
import android.view_to_model.ViewToModelActivity
import com.example.android.databinding.ActivityAndroidBinding

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
    }
}