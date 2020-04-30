package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.helpers.RemoteConfigHelper
import com.example.myapplication.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_firebase.*

class FirebaseActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        setRemoteConfigs()
        setResources()
    }

    private fun setResources(){
        firebase_title_toolbar.text = RemoteConfigHelper().firebaseToolbarTitle
    }

    private fun setRemoteConfigs(){
        RemoteConfigHelper().setConfigs()
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, FirebaseActivity::class.java))
    }
}