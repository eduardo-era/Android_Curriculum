package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.helpers.RemoteConfigHelper
import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.presenters.RealtimeDatabasePresenter
import com.example.myapplication.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_realtime_database.*

class RealtimeDatabaseView:BaseActivity(),RealtimeDatabase.View {

    private val presenter = RealtimeDatabasePresenter(this)

    var hintName: String? = null
    var hintAmount: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_database)
        init()
    }

    private fun init(){
        setRemoteConfigResources()
        hintText()
        clickSendRealtime()
    }

    private fun hintText(){
        realtime_database_cleate_name.hint = hintName
        realtime_database_create_crdit.hint = hintAmount
    }

    private fun setRemoteConfigResources(){
        realtime_title_toolbar.text = RemoteConfigHelper().realtimeDatabase
        hintName = RemoteConfigHelper().ingressName
        hintAmount = RemoteConfigHelper().ingressAmount
        realtime_database_create_button.text = RemoteConfigHelper().send
    }

    private fun clickSendRealtime(){
        realtime_database_create_button.setOnClickListener {
            sendToRealtime()
        }
    }

    private fun sendToRealtime(){
        val name = realtime_database_cleate_name.text.toString()
        val amount = realtime_database_create_crdit.text.toString()
        realtime_database_cleate_name.text.clear()
        realtime_database_create_crdit.text.clear()
        hintText()
        presenter.sendDataToRealtime(name, amount)
    }

    override fun fieldsEmpty() {
        Toast.makeText(this,resources.getString(R.string.empty_fields),Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, RealtimeDatabaseView::class.java))
    }
}