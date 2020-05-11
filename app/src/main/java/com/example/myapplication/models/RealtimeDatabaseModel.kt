package com.example.myapplication.models

import android.util.Log
import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.pojos.Customers
import com.example.myapplication.presenters.RealtimeDatabasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RealtimeDatabaseModel(val presenter: RealtimeDatabasePresenter): RealtimeDatabase.Model {

    override fun sendDataToRealtime(name: String, amount: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customers").child(name)
        val customers = Customers()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("RealTIme", "Failed to write value.", p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value == null){
                    customers.customername = name
                    customers.maxcredit = amount.toDouble()
                    myRef.setValue(customers)
                }
            }
        })
    }
}