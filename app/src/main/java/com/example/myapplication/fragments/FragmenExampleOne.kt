package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.R

class FragmenExampleOne : Fragment() {

    var webViewExampleOne: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_example_one, container, false)
        webViewExampleOne = v.findViewById(R.id.fragment_activity_example_one_webview)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPageWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setPageWebView(){
        webViewExampleOne?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webViewExampleOne?.settings?.javaScriptEnabled = true
        webViewExampleOne?.loadUrl("https://www.youtube.com/")
    }
}