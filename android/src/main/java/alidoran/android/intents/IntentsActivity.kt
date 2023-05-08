package alidoran.android.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android.databinding.ActivityIntentsBinding
import android.content.Intent

class IntentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent(): Unit = with(binding){
        btnOsUpdate.setOnClickListener{
            startActivity(Intent("android.settings.SYSTEM_UPDATE_SETTINGS"))
        }

        btnSendEmail.setOnClickListener {
            sendEMail("Hello", "alidoran@gmail.com", "Hello World")
        }
    }

    private fun sendEMail(subject: String, to: String, email: String?) {
        val address = arrayOf(to)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_TEXT, email)
        startActivity(intent)
    }
}