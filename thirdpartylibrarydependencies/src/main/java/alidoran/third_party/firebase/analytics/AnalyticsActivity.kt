package alidoran.third_party.firebase.analytics

import alidoran.third_party.databinding.ActivityAnalyticsBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import java.util.*


class AnalyticsActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding: ActivityAnalyticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        analytics = Firebase.analytics
        initEvent()
    }

    private fun initEvent() {
        binding.btnAction.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, 123)
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, Calendar.getInstance().time.toString())
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT , bundle)
        }

    }


}