package com.example.myapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R


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

        fun informationDialog(activity: Activity,content: String){
            val dialog = AlertDialog.Builder(activity)
            val viewInformation = activity.layoutInflater.inflate(R.layout.dialog_information, null)

            val contentDialog = viewInformation.findViewById<TextView>(R.id.information_content)
            val buttonDialog = viewInformation.findViewById<Button>(R.id.information_button)

            if(content.isNotEmpty()){
                contentDialog.text = content
            }

            dialog.setView(viewInformation)
            val alertDialog = dialog.create()
            alertDialog.window?.setBackgroundDrawableResource(R.drawable.rounded_corners_edge_blue)

            buttonDialog.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}


