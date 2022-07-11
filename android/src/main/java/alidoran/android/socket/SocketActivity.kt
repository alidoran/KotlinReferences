package alidoran.android.socket


import alidoran.android.databinding.ActivitySocketBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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