package com.example.myapplication.presenters

import com.example.myapplication.interfaces.RealtimeDatabase
import com.example.myapplication.models.RealtimeDatabaseModel
import com.example.myapplication.pojos.Customers
import com.example.myapplication.views.RealtimeDatabaseView
import java.util.ArrayList

class RealtimeDatabasePresenter(val view: RealtimeDatabaseView):RealtimeDatabase.Presenter {

    private val model = RealtimeDatabaseModel(this)

    override fun sendDataToRealtime(id:String, name: String, amount: String) {
        if(name.isEmpty() || amount.isEmpty()){
            view.fieldsEmpty()
        }else{
            model.sendDataToRealtime(id, name, amount.toDouble())
        }
    }

    override fun dataSended() {
        view.dataSended()
    }

    override fun duplicatedIdFound(id:String, name: String, amount: String) {
        view.valuesExist(id, name, amount)
    }

    override fun updateExistingClient(id:String, name: String, amount: String) {
        if(name.isEmpty() || amount.isEmpty()){
            view.fieldsEmpty()
        }else{
            model.updateDataRealtime(id, name, amount)
        }
    }

    override fun obtainedDataRealtime(customers: ArrayList<Customers>) {
        view.realtimeObtainedCustomers(customers)
    }

    override fun getDataRealtime() {
        model.getDataRealtime()
    }

    override fun deleteDataRealtime(id: String) {
        model.deleteDataRealtime(id)
    }
}