package com.example.myapplication.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Pokemon {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null
}