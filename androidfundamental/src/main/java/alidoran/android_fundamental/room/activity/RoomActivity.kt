package alidoran.android_fundamental.room.activity

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.os.Bundle
import alidoran.android_fundamental.R
import alidoran.android_fundamental.databinding.ActivityRoomBinding
import android.content.Intent
import alidoran.android_fundamental.room.model.Contact
import android.view.View

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }


    private fun initEvent() {
        binding.btnSave.setOnClickListener {
            val intent = Intent(this, SaveDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createSampleContacts(): Contact {
        return Contact(
            null,
            "ali",
            "doran",
            "+989124955481"
        )

    }
}