package com.example.myapplication.helpers

import com.example.myapplication.R
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class RemoteConfigHelper {

    private var remoteConfig: FirebaseRemoteConfig? = null

    init {
        remoteConfig = getRemoteConfigInstance()
    }

    var firebaseToolbarTitle : String = getRemoteConfigInstance().getString("firebase")
    var firebaseInformation: String = getRemoteConfigInstance().getString("firebase_information")
    var cloudMessaging: String = getRemoteConfigInstance().getString("cloud_messaging")
    var realtimeDatabase:String = getRemoteConfigInstance().getString("realtime_database")
    var infoCloudMessaging: String = getRemoteConfigInstance().getString("cloud_messaging_information")
    var ingressName: String = getRemoteConfigInstance().getString("enter_name")
    var ingressAmount: String = getRemoteConfigInstance().getString("enter_amount")
    var send: String = getRemoteConfigInstance().getString("send_high")

    private fun getRemoteConfigInstance(): FirebaseRemoteConfig {
        if (remoteConfig == null) {
            remoteConfig = FirebaseRemoteConfig.getInstance()
            remoteConfig?.setDefaults(R.xml.remote_configs_defaults)
        }
        return remoteConfig!!
    }

    fun setConfigs(){
        getRemoteConfigInstance().fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                assignValues(getRemoteConfigInstance())
            }
        }
    }

    private fun assignValues(remoteConfig: FirebaseRemoteConfig){
        firebaseToolbarTitle = remoteConfig.getString("firebase")
        firebaseInformation = remoteConfig.getString("firebase_information")
        cloudMessaging = remoteConfig.getString("cloud_messaging")
        infoCloudMessaging = remoteConfig.getString("cloud_messaging_information")
        realtimeDatabase = remoteConfig.getString("realtime_database")
        ingressName = getRemoteConfigInstance().getString("enter_name")
        ingressAmount = getRemoteConfigInstance().getString("enter_amount")
        send = getRemoteConfigInstance().getString("send_high")
    }

}