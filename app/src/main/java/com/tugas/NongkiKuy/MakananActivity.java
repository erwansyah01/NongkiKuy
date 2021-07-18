package com.tugas.NongkiKuy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

public class MakananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);
        //identifies the webview
        WebView mWebView = (WebView) findViewById(R.id.webviewmakanan);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //loads the url
        mWebView.loadUrl("192.168.1.6/Cafe");

        //cancels all hyperlinks
        mWebView.setWebViewClient(new WebViewClient() {
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          return false;
                                      }
                                  }
        );
    }
}
