package alidoran.third_party.pendo

import alidoran.third_party.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PendoActivity : AppCompatActivity() {

    var visitorId = "My_VISITOR-UNIQUE-ID"
    var accountId = "My_ACCOUNT-UNIQUE-ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendo)

//        // send Visitor Level Data
//        val visitorData: HashMap<String, Any> = HashMap()
//        visitorData["age"] = 27
//        visitorData["country"] = "USA"
//
//        // send Account Level Data
//        val accountData: HashMap<String, Any> = HashMap()
//        accountData["Tier"] = 1
//        accountData["Size"] = "Enterprise"
//
//        Pendo.startSession(
//            visitorId,
//            accountId,
//            visitorData,
//            accountData
//        )

        //Device log for checking Pendo:
        // PendoInternal: Pendo Mobile SDK was successfully integrated and connected to the server. App version identified: '<version>'.
    }
}