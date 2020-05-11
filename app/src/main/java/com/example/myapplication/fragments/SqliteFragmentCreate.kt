package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.pojos.Customers
import com.example.myapplication.utils.GeneralUtilities
import com.example.myapplication.views.SqliteActivity

class SqliteFragmentCreate : Fragment() {

    var sqliteCreateName: EditText? = null
    var sqliteCreateAmount: EditText? = null
    var sqliteCreateButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sqlite_create, container, false)
        sqliteCreateName = view.findViewById(R.id.sqlite_cleate_name)
        sqliteCreateAmount = view.findViewById(R.id.sqlite_create_crdit)
        sqliteCreateButton = view.findViewById(R.id.sqlite_create_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickCreateButton()
        setImeActionDone(sqliteCreateAmount!!)
    }

    private fun clearData(){
        sqliteCreateAmount!!.text.clear()
        sqliteCreateName!!.text.clear()
        sqliteCreateName!!.hint = resources.getString(R.string.enter_name)
        sqliteCreateAmount!!.hint = resources.getString(R.string.enter_amount)
    }

    private fun clickCreateButton(){
        sqliteCreateButton!!.setOnClickListener {
            sendToCreate()
        }
    }

    private fun setImeActionDone(editTextToSetIMA: EditText) {
        editTextToSetIMA.imeOptions = EditorInfo.IME_ACTION_DONE
        editTextToSetIMA.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendToCreate()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun sendToCreate(){
        if (sqliteCreateAmount!!.text.isNullOrEmpty() && sqliteCreateName!!.text.isNullOrEmpty() )
        {
            Toast.makeText(requireContext(), R.string.empty_fields, Toast.LENGTH_SHORT).show()
            sqliteCreateName!!.requestFocus()

        }else{
            val customer = Customers()
            customer.customername = sqliteCreateName!!.text.toString()
            customer.maxcredit = sqliteCreateAmount!!.text.toString().toDouble()
            GeneralUtilities.hideKeyboardFrom(requireContext(),sqliteCreateName)
            SqliteActivity.dbHandler.addCustomer(requireContext(),customer)
            sqliteCreateName!!.requestFocus()
            clearData()
        }
    }
}
