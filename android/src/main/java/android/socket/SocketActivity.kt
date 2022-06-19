package android.socket


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.databinding.ActivitySocketBinding

class SocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Server().run()
        binding.btnSendDataToServer.setOnClickListener {
            Client().run()
        }


    }

}