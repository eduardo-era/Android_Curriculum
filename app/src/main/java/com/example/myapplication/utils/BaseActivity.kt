package com.example.myapplication.utils

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

open class BaseActivity: AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(getString(R.string.loading))
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
    }

    fun showProgress() {
        progressDialog?.let {
            if (!it?.isShowing) {
                it?.show()
            }
        }
    }

    fun dismissProgress() {
        progressDialog?.let {
            if (it.isShowing && !isFinishing) {
                try {
                    it.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}