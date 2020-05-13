package com.example.myapplication.views

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.helpers.RemoteConfigHelper
import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.presenters.RealtimeDatabasePresenter
import com.example.myapplication.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_realtime_database.*
import kotlinx.android.synthetic.main.sqlite_update_customers_dialog.view.*
import java.time.temporal.TemporalAmount

class RealtimeDatabaseView:BaseActivity(),RealtimeDatabase.View {

    private val presenter = RealtimeDatabasePresenter(this)

    var hintName: String? = null
    var hintAmount: String? = null
    var name: String? = null
    var amount: String? = null

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

    private fun setRemoteConfigResources(){
        realtime_title_toolbar.text = RemoteConfigHelper().realtimeDatabase
        hintName = RemoteConfigHelper().ingressName
        hintAmount = RemoteConfigHelper().ingressAmount
        realtime_database_create_button.text = RemoteConfigHelper().send
    }

    private fun hintText(){
        realtime_database_cleate_name.hint = hintName
        realtime_database_create_crdit.hint = hintAmount
    }

    private fun clickSendRealtime(){
        realtime_database_create_button.setOnClickListener {
            sendToRealtime()
        }
    }

    private fun sendToRealtime(){
        name = realtime_database_cleate_name.text.toString()
        amount = realtime_database_create_crdit.text.toString()
        realtime_database_cleate_name.text.clear()
        realtime_database_create_crdit.text.clear()
        hintText()
        presenter.sendDataToRealtime(name!!, amount!!)
    }

    override fun fieldsEmpty() {
        Toast.makeText(this,resources.getString(R.string.empty_fields),Toast.LENGTH_SHORT).show()
    }

    override fun dataSended() {
        Toast.makeText(this,resources.getString(R.string.client_sended),Toast.LENGTH_SHORT).show()
    }

    override fun valuesExist() {
        AlertDialog.Builder(this).setTitle("ATENCIÓN")
            .setMessage("El id de cliente que ha ingresado ya existe").setPositiveButton("Actualizar", DialogInterface.OnClickListener { _, _ ->
                updateDataCustomer(name!!,amount!!)
            })
            .setNegativeButton("Ingresar uno nuevo", DialogInterface.OnClickListener { Dialog, _ ->
                Dialog.dismiss()
            })
            .setIcon(R.drawable.warning)
            .show()
    }

    override fun updateDataCustomer(name:String, amount: String){
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.sqlite_update_customers_dialog,null)
        val textname = view.findViewById<EditText>(R.id.sqlite_update_name_text)
        val txtCredit = view.findViewById<EditText>(R.id.sqlite_update_credit_text)

        textname.setText(name)
        txtCredit.setText(amount)

        AlertDialog.Builder(this).setTitle("Actualizar Cliente")
            .setView(view)
            .setPositiveButton("ACTUALIZAR", DialogInterface.OnClickListener { _, _ ->
                presenter.updateExistingClient(textname.text.toString(), txtCredit.text.toString())
            }).setNegativeButton("Cancelar", DialogInterface.OnClickListener { Dialog, _ ->
                Dialog.dismiss()
            })
            .show()
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, RealtimeDatabaseView::class.java))
    }
}