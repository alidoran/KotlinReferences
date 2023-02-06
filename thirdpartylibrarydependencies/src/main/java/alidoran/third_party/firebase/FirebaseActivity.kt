package alidoran.third_party.firebase

import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.databinding.ActivityFirebaseBinding
import alidoran.third_party.firebase.analytics.AnalyticsActivity
import alidoran.third_party.firebase.authenticate.FirebaseAuthenticate
import alidoran.third_party.firebase.crashlytics.CrashlyticsActivity
import alidoran.third_party.firebase.fcm_push_notification.FcmPushNotificationActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FirebaseActivity : AppCompatActivity() {

    lateinit var binding: ActivityFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppStatusHelp(this).getIsAppInBackground()
        initEvent()
    }

    private fun initEvent() {
        binding.btnAnalytics.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }

        binding.btnAuthenticate.setOnClickListener {
            val intent = Intent(this, FirebaseAuthenticate::class.java)
            startActivity(intent)
        }

        binding.btnFmc.setOnClickListener {
            val intent = Intent(this, FcmPushNotificationActivity::class.java)
            startActivity(intent)
        }

        binding.btnCrashlytics.setOnClickListener {
            val intent = Intent(this, CrashlyticsActivity::class.java)
            startActivity(intent)
        }
    }
}