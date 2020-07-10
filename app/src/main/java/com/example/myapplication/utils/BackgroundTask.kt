package com.example.myapplication.utils

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.myapplication.interfaces.Async

class BackgroundTask(val context: Context, val handler: () -> Unit):AsyncTask<Void,Void,Void>() {

    override fun doInBackground(vararg p0: Void?): Void? {
        handler()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Toast.makeText(context, "Termino", Toast.LENGTH_SHORT).show()
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}