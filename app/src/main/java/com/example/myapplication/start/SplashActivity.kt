package com.example.myapplication.start

import android.os.Bundle
import android.os.Handler
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.views.MainActivity

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToMainActivity()
    }

    private fun  goToMainActivity(){
        Handler().postDelayed({
            MainActivity.start(this)
            finish()
        },1000)
    }
}