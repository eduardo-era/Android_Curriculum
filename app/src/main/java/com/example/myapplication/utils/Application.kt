package com.example.myapplication.utils

import android.os.Bundle

class Application:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun getInstance(): Application {
            if (instance == null) {
                instance = Application()
            }
            return instance!!
        }
    }
}