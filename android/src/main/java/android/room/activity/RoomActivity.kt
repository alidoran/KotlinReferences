package android.room.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.databinding.ActivityRoomBinding
import android.content.Intent
import android.room.model.Contact

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