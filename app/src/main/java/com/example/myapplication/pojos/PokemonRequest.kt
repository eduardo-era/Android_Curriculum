package com.example.myapplication.pojos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class PokemonRequest {

    @SerializedName("results")
    var pokemon: ArrayList<Pokemon>? = null
}