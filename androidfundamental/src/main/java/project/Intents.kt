package project

import android.app.Activity
import android.content.Intent

class Intents: Activity() {
    fun sendEMail(subject: String, to: String, email: String?) {
        val address = arrayOf(to)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_TEXT, email)
        startActivity(intent)
    }
}