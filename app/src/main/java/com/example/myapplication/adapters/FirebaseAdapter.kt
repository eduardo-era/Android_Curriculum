package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.pojos.Customers
import com.example.myapplication.views.RealtimeDatabaseView
import kotlinx.android.synthetic.main.sqlite_update_costumers.view.*

class FirebaseAdapter(val customers: ArrayList<Customers>, val view : RealtimeDatabaseView): RecyclerView.Adapter<FirebaseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sqlite_update_costumers, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: FirebaseAdapter.ViewHolder, position: Int) {
        val customer = customers[position]
        holder.updateName.text = customer.customername
        holder.updateCredit.text = customer.maxcredit.toString()

        holder.updateButton.setOnClickListener {
            view.updateDataCustomer(customer.customerid.toString(),customer.customername!!,customer.maxcredit.toString())
        }

        holder.deleteBurron.setOnClickListener {
            view.deleteDataRealtime(customer.customerid.toString())
            customers.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, customers.size)
        }
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val updateName: TextView = itemView.sqlite_update_name
        val updateCredit: TextView = itemView.sqlite_update_credit
        val updateButton: Button = itemView.sqlite_update_button
        val deleteBurron: Button = itemView.sqlite_delete_button
    }
}