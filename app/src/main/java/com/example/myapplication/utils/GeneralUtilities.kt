package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager


class GeneralUtilities {
    companion object {

        fun hideKeyboardFrom(context: Context, view: View?) {
            try {
                view?.let {
                    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        fun isNetworkAvaliable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
        }
    }
}


