package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.GeneralUtilities
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitView:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        init()
    }

    private fun init(){
        clickExampleOne()
        clickHelpRetrofit()
    }

    private fun clickExampleOne(){
        retrofit_button_example_one.setOnClickListener {
            RetrofitExampleOneView.start(this)
        }
    }

    private fun clickHelpRetrofit(){
        retrofit_help.setOnClickListener {
            GeneralUtilities.informationDialog(this,resources.getString(R.string.retrofit_main_help))
        }
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, RetrofitView::class.java))
    }
}