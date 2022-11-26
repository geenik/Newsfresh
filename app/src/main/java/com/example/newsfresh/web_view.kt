package com.example.newsfresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient




class web_view : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val extras = intent.extras
        var url=""
        if (extras != null) {
             url = extras.getString("key").toString()
        }
        webView=findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if(webView.canGoBack())webView.goBack()
        else super.onBackPressed()
    }
}