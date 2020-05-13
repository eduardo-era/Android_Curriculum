package com.example.myapplication.interfaces

import java.time.temporal.TemporalAmount

class RealtimeDatabase {
    interface View {
        fun fieldsEmpty()
        fun dataSended()
        fun valuesExist()
        fun updateDataCustomer(name:String, amount: String)
    }

    interface Presenter {
        fun sendDataToRealtime(name:String, amount: String)
        fun dataSended()
        fun duplicatedIdFound()
        fun updateExistingClient(name:String, amount: String)
    }

    interface Model {
        fun sendDataToRealtime(name:String, amount: String)
        fun updateDataRealtime(name: String, amount: String)
    }
}