package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.SqliteCustomersAdapter
import com.example.myapplication.handlers.DataBaseHandler
import com.example.myapplication.views.SqliteActivity

class SqliteFragmentUpdate : Fragment() {

    var recyclerSqlite: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sqlite_update, container, false)
        recyclerSqlite = view.findViewById(R.id.sqlite_recycler_update)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCustomers()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            viewCustomers()
        }
    }

    fun viewCustomers(){
        val customersList = SqliteActivity.dbHandler.getCustomers(requireContext())
        val adapter = SqliteCustomersAdapter(requireContext(),customersList)
        val rv: RecyclerView = recyclerSqlite!!
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv.adapter = adapter
    }
}
