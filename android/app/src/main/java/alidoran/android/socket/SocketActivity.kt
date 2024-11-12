package alidoran.android.socket


import alidoran.android.databinding.ActivitySocketBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendDataToServer.setOnClickListener {
            runClient()
        }



    }
    private fun runClient(){
        CoroutineScope(IO).launch {
            Client().run()
        }
    }
}