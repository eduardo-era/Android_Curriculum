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
    }

}