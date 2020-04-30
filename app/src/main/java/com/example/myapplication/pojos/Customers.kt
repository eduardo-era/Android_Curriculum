package com.example.myapplication.pojos

import com.google.gson.annotations.SerializedName

class Customers {
    @SerializedName("customerID")
    var customerid: Int? = null

    @SerializedName("customerName")
    var customername : String? = null

    @SerializedName("maxCredit")
    var maxcredit : Double? = 0.0
}