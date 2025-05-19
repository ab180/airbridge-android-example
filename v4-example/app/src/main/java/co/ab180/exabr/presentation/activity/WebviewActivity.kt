package co.ab180.exabr.presentation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import co.ab180.airbridge.Airbridge
import co.ab180.exabr.BuildConfig
import co.ab180.exabr.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    private val webChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.progressBar.visibility = if (newProgress < 100) View.VISIBLE else View.GONE
            binding.progressBar.progress = newProgress
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.webChromeClient = webChromeClient

        Airbridge.setWebInterface(binding.webView, BuildConfig.WEB_SDK_TOKEN)

        if (binding.webView.url.isNullOrEmpty()) {
            binding.webView.loadUrl(BuildConfig.HYBRID_TEST_WEB_URL)
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, WebviewActivity::class.java)
        }
    }
}