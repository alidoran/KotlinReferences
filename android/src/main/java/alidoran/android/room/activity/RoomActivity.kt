package alidoran.android.room.activity

import alidoran.android.databinding.ActivityRoomBinding
import alidoran.android.room.model.Contact
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

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