package com.example.myapplication.models

import android.util.Log
import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.pojos.Customers
import com.example.myapplication.presenters.RealtimeDatabasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class RealtimeDatabaseModel(val presenter: RealtimeDatabasePresenter): RealtimeDatabase.Model {

    override fun getDataRealtime() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customers")
        val customerObtained = Customers()

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("RealTIme", "Failed to get values.", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val customers = ArrayList<Customers>()
                if(p0.exists()){
                    for (i in p0.children){
                        val customer =  i.getValue(Customers::class.java)
                        customers.add(customer!!)
                    }
                    presenter.obtainedDataRealtime(customers)
                }
            }
        })
    }

    override fun sendDataToRealtime(id:String, name: String, amount: Double) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customers").child(id)
        val customers = Customers()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("RealTIme", "Failed to write value.", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value == null){
                    customers.customerid = id.toInt()
                    customers.customername = name
                    customers.maxcredit = amount
                    myRef.setValue(customers)
                    presenter.dataSended()
                }else{
                    val customer = p0.getValue(Customers::class.java)
                    val idExist = p0.key
                    val nameExist = customer!!.customername
                    val amountExist = customer.maxcredit

                    presenter.duplicatedIdFound(idExist.toString(),nameExist!!,amountExist.toString())
                }
            }
        })
    }

    override fun updateDataRealtime(id: String, name: String, amount: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customers").child(id)
        val updateInfo = TreeMap<String, Any>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("RealTIme", "Failed to update value.", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null){
                    updateInfo["customername"] = name
                    updateInfo["maxcredit"] = amount.toDouble()
                    myRef.updateChildren(updateInfo)
                    presenter.dataSended()
                }
            }
        })
    }

    override fun deleteDataRealtime(id: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customers").child(id)
        myRef.removeValue()
    }
}