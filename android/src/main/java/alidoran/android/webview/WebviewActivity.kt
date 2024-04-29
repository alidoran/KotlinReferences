package alidoran.android.webview

import alidoran.android.R
import alidoran.android.databinding.ActivityWebViewBinding
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add the first line code in the manifest application to solve the second line error
        // 1- android:usesCleartextTraffic="true"
        // 2- ERR_CLEARTEXT_NOT_PERMITTED

        // give internet access by the following line
        // <uses-permission android:name="android.permission.INTERNET" />

        binding.btnShowOnWebview.setOnClickListener {
            val myWebView: WebView = findViewById(R.id.webview)
            myWebView.loadUrl("http://www.goole.com")
        }

        binding.btnShowOnActivity.setOnClickListener {
            val myWebView = WebView(this)
            setContentView(myWebView)
            myWebView.loadUrl("http://www.goole.com")
        }

        binding.btnLoadFromHtmlString.setOnClickListener {
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

        binding.btnGetJavascriptResult.setOnClickListener { webviewInActivity() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webviewInActivity() {
        val myWebView: WebView = findViewById(R.id.webview)
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        myWebView.webViewClient = webViewClient
        myWebView.addJavascriptInterface(JSInterface(), "JSInterface")

        myWebView.loadUrl("file:///android_res/raw/index.html")
    }

    private val webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("webviewInActivity", "onPageFinished: $url")
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.d("webviewInActivity", "onPageStarted: $url")
        }
    }

    class JSInterface internal constructor() {
        @JavascriptInterface
        fun toastMe(text: String?) {
            Log.d("WebviewActivity", "toastMe: $text")
        }

        @JavascriptInterface
        fun notifyMe(text: String?) {
            Log.d("WebviewActivity", "toastMe: $text")
        }
    }
}