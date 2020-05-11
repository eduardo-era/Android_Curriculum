package com.example.myapplication.interfaces

import java.time.temporal.TemporalAmount

class RealtimeDatabase {
    interface View {
        fun fieldsEmpty()
    }

    interface Presenter {
        fun sendDataToRealtime(name:String, amount: String)
    }

    interface Model {
        fun sendDataToRealtime(name:String, amount: String)
    }
}