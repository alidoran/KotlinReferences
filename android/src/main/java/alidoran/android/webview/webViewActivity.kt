package alidoran.android.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import alidoran.android.R
import alidoran.android.databinding.ActivityWebViewBinding
import android.util.Base64
import android.webkit.WebView

class webViewActivity : AppCompatActivity() {
    lateinit var binding : ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add the first line code in the manifest application to solve the second line error
        // 1- android:usesCleartextTraffic="true"
        // 2- ERR_CLEARTEXT_NOT_PERMITTED

        //give internet access by the following line
        // <uses-permission android:name="android.permission.INTERNET" />

        binding.btnShowOnWebview.setOnClickListener{
            val myWebView: WebView = findViewById(R.id.webview)
            myWebView.loadUrl("http://www.goole.com")
        }

        binding.btnShowOnActivity.setOnClickListener {
            val myWebView = WebView(this)
            setContentView(myWebView)
            myWebView.loadUrl("http://www.goole.com")
        }

        binding.btnLoadFromHtmlString.setOnClickListener{
            val myWebView: WebView = findViewById(R.id.webview)
            val unencodedHtml =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Hello World Example</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>Hello World!</h1>\n" +
                        "</body>\n" +
                        "</html>"
            val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
            myWebView.loadData(encodedHtml, "text/html", "base64")
        }




    }
}