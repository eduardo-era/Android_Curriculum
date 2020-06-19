package com.example.myapplication.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Customers {
    @SerializedName("customerID")
    var customerid: Int? = null

    @SerializedName("customerName")
    var customername : String? = null

    @SerializedName("maxCredit")
    var maxcredit : Double? = 0.0
}