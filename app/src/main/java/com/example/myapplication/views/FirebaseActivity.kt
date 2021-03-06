package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.helpers.RemoteConfigHelper
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.GeneralUtilities
import kotlinx.android.synthetic.main.activity_firebase.*

class FirebaseActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)
        init()
    }

    private fun init(){
        setRemoteConfigs()
        setResources()
        clickFirebaseInformation()
        clickCloudMessaging()
        clickRealtimeDatabase()
        clickRemoteConfigButton()
    }

    private fun setRemoteConfigs(){
        RemoteConfigHelper().setConfigs()
    }

    private fun setResources(){
        firebase_title_toolbar.text = RemoteConfigHelper().firebaseToolbarTitle
        firebase_button_cloud_message.text = RemoteConfigHelper().cloudMessaging
        firebase_button_real_time_database.text = RemoteConfigHelper().realtimeDatabase
        firebase_button_remote_config.text = RemoteConfigHelper().remoteConfigTextButton
    }

    private fun clickFirebaseInformation(){
        firebase_help.setOnClickListener {
            GeneralUtilities.informationDialog(this, resources.getString(R.string.firebase_information))
        }
    }

    private fun clickCloudMessaging(){
        firebase_button_cloud_message.setOnClickListener {
            GeneralUtilities.informationDialog(this, RemoteConfigHelper().infoCloudMessaging)
        }
    }

    private fun clickRemoteConfigButton(){
        firebase_button_remote_config.setOnClickListener {
            GeneralUtilities.informationDialog(this,RemoteConfigHelper().firebaseInformation)
        }
    }

    private fun clickRealtimeDatabase(){
        firebase_button_real_time_database.setOnClickListener {
            RealtimeDatabaseView.start(this)
        }
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, FirebaseActivity::class.java))
    }
}