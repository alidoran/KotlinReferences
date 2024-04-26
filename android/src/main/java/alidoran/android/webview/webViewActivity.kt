package alidoran.android.webview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import alidoran.android.R
import android.webkit.WebView

class webViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)

        // add the first line code in the manifest application to solve the second line error
        // 1- android:usesCleartextTraffic="true"
        // 2- ERR_CLEARTEXT_NOT_PERMITTED
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl("http://www.goole.com")
    }
}