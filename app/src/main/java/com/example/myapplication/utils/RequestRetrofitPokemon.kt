package com.example.myapplication.utils

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestRetrofitPokemon {
    companion object{
        fun url(context: Context):Retrofit{
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}