package co.ab180.exabr.presentation.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.ab180.airbridge.Airbridge;
import co.ab180.exabr.BuildConfig;
import co.ab180.exabr.databinding.ActivityWebviewBinding;

public class WebviewActivity extends AppCompatActivity {

    private ActivityWebviewBinding binding;

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress < 100) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
            binding.progressBar.setProgress(newProgress);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWebviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setDomStorageEnabled(true);
        binding.webView.setWebChromeClient(webChromeClient);

        Airbridge.setJavascriptInterface(binding.webView, BuildConfig.WEB_SDK_TOKEN);

        String url = binding.webView.getUrl();
        if (url != null && !url.isEmpty()) {
            binding.webView.loadUrl(BuildConfig.HYBRID_TEST_WEB_URL);
        }
    }

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, WebviewActivity.class);
    }
}
