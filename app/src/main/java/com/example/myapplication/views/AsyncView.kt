package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity

class AsyncView:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activuty_async)
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, AsyncView::class.java))
    }
}