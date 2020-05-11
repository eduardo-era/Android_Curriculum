package com.example.myapplication.presenters

import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.models.RealtimeDatabaseModel
import com.example.myapplication.views.RealtimeDatabaseView

class RealtimeDatabasePresenter(val view: RealtimeDatabaseView):RealtimeDatabase.Presenter {

    val model = RealtimeDatabaseModel(this)

    override fun sendDataToRealtime(name: String, amount: String) {
        if(name.isEmpty() || amount.isEmpty()){
            view.fieldsEmpty()
        }else{
            model.sendDataToRealtime(name, amount)
        }
    }
}