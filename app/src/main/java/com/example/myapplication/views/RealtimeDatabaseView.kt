package com.example.myapplication.views

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.FirebaseAdapter
import com.example.myapplication.helpers.RemoteConfigHelper
import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.pojos.Customers
import com.example.myapplication.presenters.RealtimeDatabasePresenter
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.GeneralUtilities
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_realtime_database.*
import kotlinx.android.synthetic.main.bottom_sheet_recycler.*
import java.util.*

class RealtimeDatabaseView:BaseActivity(),RealtimeDatabase.View {

    private val presenter = RealtimeDatabasePresenter(this)
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private var recyclerFirebase: RecyclerView? = null
    private var hintId: String? = null
    private var hintName: String? = null
    private var hintAmount: String? = null
    var name: String? = null
    var amount: String? = null
    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_database)
        init()
    }

    private fun init(){
        setRemoteConfigResources()
        hintText()
        clickSendRealtime()
        getDataRealtime()
        bottomSheetCustomers()
        recyclerFirebase = findViewById(R.id.recycler_view)
    }

    private fun setRemoteConfigResources(){
        realtime_title_toolbar.text = RemoteConfigHelper().realtimeDatabase
        hintId = RemoteConfigHelper().ingressId
        hintName = RemoteConfigHelper().ingressName
        hintAmount = RemoteConfigHelper().ingressAmount
        realtime_database_create_button.text = RemoteConfigHelper().send
    }

    private fun hintText(){
        realtime_database_cleate_id.hint = hintId
        realtime_database_cleate_name.hint = hintName
        realtime_database_create_crdit.hint = hintAmount
    }

    private fun clickSendRealtime(){
        realtime_database_create_button.setOnClickListener {
            sendToRealtime()
        }
        GeneralUtilities.setImeActionDone(realtime_database_create_crdit){sendToRealtime()}
    }

    private fun getDataRealtime() {
        presenter.getDataRealtime()
    }

    private fun sendToRealtime(){
        id = realtime_database_cleate_id.text.toString()
        name = realtime_database_cleate_name.text.toString()
        amount = realtime_database_create_crdit.text.toString()
        realtime_database_cleate_id.text.clear()
        realtime_database_cleate_name.text.clear()
        realtime_database_create_crdit.text.clear()
        hintText()
        presenter.sendDataToRealtime(id!!, name!!, amount!!)
    }

    override fun realtimeObtainedCustomers(customers: ArrayList<Customers>) {
        val adapter = FirebaseAdapter(customers, this)
        recyclerFirebase!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerFirebase!!.adapter = adapter
    }

    override fun fieldsEmpty() {
        Toast.makeText(this,resources.getString(R.string.empty_fields),Toast.LENGTH_SHORT).show()
        dismissProgress()
    }

    override fun deleteDataRealtime(id: String) {
        presenter.deleteDataRealtime(id)
    }

    override fun dataDeleted() {
        Toast.makeText(this, "Cliente Eliminado", Toast.LENGTH_SHORT).show()
        dismissProgress()
    }

    override fun dataSended() {
        Toast.makeText(this,resources.getString(R.string.client_sended),Toast.LENGTH_SHORT).show()
        dismissProgress()
        GeneralUtilities.hideKeyboardFrom(this, realtime_database_create_crdit)
    }

    override fun valuesExist(id:String, name: String, amount: String) {
        AlertDialog.Builder(this).setTitle("ATENCIÓN")
            .setMessage("El id de cliente que ha ingresado ya existe").setPositiveButton("Actualizar") { _, _ ->
                updateDataCustomer(id, name, amount)
            }
            .setNegativeButton("Ingresar uno nuevo") { Dialog, _ ->
                Dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    override fun updateDataCustomer(id:String, name: String, amount: String){
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.realtime_database_update,null)
        val textname = view.findViewById<EditText>(R.id.realtime_update_name_text)
        val textCredit = view.findViewById<EditText>(R.id.realtime_update_credit_text)

        textname.setText(name)
        textCredit.setText(amount)

        AlertDialog.Builder(this).setTitle("Actualizar Cliente")
            .setView(view)
            .setPositiveButton("ACTUALIZAR") { _, _ ->
                presenter.updateExistingClient(id, textname.text.toString(), textCredit.text.toString())
            }.setNegativeButton("Cancelar") { Dialog, _ ->
                Dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun bottomSheetCustomers() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_firebase)
        bottomSheetBehavior.peekHeight = 30
        bottomSheetBehavior.isHideable = false
        top_button?.setOnClickListener {
            when (bottomSheetBehavior.state) {
                BottomSheetBehavior.STATE_COLLAPSED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                BottomSheetBehavior.STATE_EXPANDED -> bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, RealtimeDatabaseView::class.java))
    }
}