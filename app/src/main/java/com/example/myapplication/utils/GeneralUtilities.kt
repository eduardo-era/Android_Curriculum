package com.example.myapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.text.Html
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
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

        fun formatHTML(value: String, textview: TextView) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textview.text = Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY)
            } else {
                textview.text = Html.fromHtml(value)
            }
        }

        fun setImeActionDone(editTextToSetIMA: EditText, functionToGo:() -> Unit) {
            editTextToSetIMA.imeOptions = EditorInfo.IME_ACTION_DONE
            editTextToSetIMA.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    functionToGo()
                    return@setOnEditorActionListener true
                }
                false
            }
        }

        fun hasNavBar(context: Context): Boolean {
            val resources = context.resources
            val id: Int = resources.getIdentifier("config_showNavigationBar", "bool", "android")
            return if (id > 0) {
                resources.getBoolean(id)
            } else {
                val hasMenuKey =
                    ViewConfiguration.get(context).hasPermanentMenuKey()
                val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
                !hasMenuKey && !hasBackKey
            }
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
            alertDialog.window?.setBackgroundDrawableResource(R.drawable.rounded_corners_edge_blue_background_white)

            buttonDialog.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}


