package com.example.myapplication.interfaces

import com.example.myapplication.pojos.Customers
import java.time.temporal.TemporalAmount
import java.util.ArrayList

class RealtimeDatabase {
    interface View {
        fun fieldsEmpty()
        fun dataSended()
        fun valuesExist(id: String, name: String, amount: String)
        fun updateDataCustomer(id:String, name: String, amount: String)
        fun realtimeObtainedCustomers(customers: ArrayList<Customers>)
        fun deleteDataRealtime(id: String)
    }

    interface Presenter {
        fun sendDataToRealtime(id:String, name: String, amount: String)
        fun dataSended()
        fun duplicatedIdFound(id:String, name: String, amount: String)
        fun updateExistingClient(id:String, name: String, amount: String)
        fun obtainedDataRealtime(customers: ArrayList<Customers>)
        fun getDataRealtime()
        fun deleteDataRealtime(id: String)
    }

    interface Model {
        fun getDataRealtime()
        fun sendDataToRealtime(id:String, name: String, amount: Double)
        fun updateDataRealtime(id:String, name: String, amount: String)
        fun deleteDataRealtime(id: String)
    }
}