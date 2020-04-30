package com.example.myapplication.handlers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.myapplication.pojos.Customers
import java.lang.Exception

class DataBaseHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createCustomerTable = ("CREATE TABLE $CUSTOMER_TABLE_NAME (" +
                "${COLUMN_CUSTOMERID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMER_NAME TEXT," +
                "$COLUMN_MAX_CREDIT DOUBLE DEFAULT 0)")

        db?.execSQL(createCustomerTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addCustomer(context: Context, customers: Customers){
        val values = ContentValues()
        values.put(COLUMN_CUSTOMER_NAME, customers.customername)
        values.put(COLUMN_MAX_CREDIT, customers.maxcredit)
        val db = this.writableDatabase
        try {
            db.insert(CUSTOMER_TABLE_NAME, null, values)
            Toast.makeText(context, "Cliente Agregado", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun getCustomers(context: Context): ArrayList<Customers>{
        val qry = "SELECT * FROM $CUSTOMER_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customers>()

        if (cursor.count != 0){
            while (cursor.moveToNext())
            {
                val customer = Customers()
                customer.customerid = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                customer.customername = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME))
                customer.maxcredit = cursor.getDouble(cursor.getColumnIndex(COLUMN_MAX_CREDIT))
                customers.add(customer)
            }
        }
        cursor.close()
        db.close()
        return customers
    }

    fun deleteCustomer(customerID: Int):Boolean{
        val qry = "DELETE FROM $CUSTOMER_TABLE_NAME WHERE $COLUMN_CUSTOMERID = $customerID"
        val db = this.writableDatabase
        var result:Boolean = false
        try {
            val cursor = db.execSQL(qry)
            result = true
        }catch (e:Exception){
            e.printStackTrace()
        }
        db.close()
        return result
    }

    fun updateCustomer(id: String, customerName: String, maxCredit:String): Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result:Boolean = false
        contentValues.put(COLUMN_CUSTOMER_NAME,customerName)
        contentValues.put(COLUMN_MAX_CREDIT,maxCredit.toDouble())
        try {
            db.update(CUSTOMER_TABLE_NAME, contentValues, "$COLUMN_CUSTOMERID = ?", arrayOf(id))
            result = true
        }catch (e:Exception){
            e.printStackTrace()
        }
        return result
    }

    companion object{
        private val DATABASE_NAME = "Mydata.db"
        private val DATABASE_VERSION = 1
        val CUSTOMER_TABLE_NAME = "Customers"
        val COLUMN_CUSTOMERID = "customerid"
        val COLUMN_CUSTOMER_NAME = "customername"
        val COLUMN_MAX_CREDIT =  "maxcredit"
    }
}