package com.example.myapplication.utils

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotification: FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        Log.e("Firebase token", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
    }

}