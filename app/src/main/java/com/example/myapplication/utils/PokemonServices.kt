package com.example.myapplication.utils

import androidx.annotation.Keep
import com.example.myapplication.pojos.Pokemon
import com.example.myapplication.pojos.PokemonRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface PokemonServices {

    @GET("pokemon")
    fun getPokemonList(@Query("limit")limit:Int, @Query("offset")offSet:Int):Call<PokemonRequest>

}
