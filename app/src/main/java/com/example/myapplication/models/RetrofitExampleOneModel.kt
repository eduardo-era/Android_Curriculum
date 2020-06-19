package com.example.myapplication.models

import android.util.Log
import com.example.myapplication.interfaces.RetrofitExampleOne
import com.example.myapplication.pojos.PokemonRequest
import com.example.myapplication.presenters.RetrofitExampleOnePresenter
import com.example.myapplication.utils.Application
import com.example.myapplication.utils.PokemonServices
import com.example.myapplication.utils.RequestRetrofitPokemon
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitExampleOneModel(val presenter: RetrofitExampleOnePresenter): RetrofitExampleOne.Model {

    private val pokemonServices: PokemonServices = RequestRetrofitPokemon.url(Application.getInstance()).create(PokemonServices::class.java)

    override fun getPokemonList(offset:Int) {
        val callPokemon = pokemonServices.getPokemonList(20,offset)
        callPokemon.enqueue(object : Callback<PokemonRequest>{
            override fun onFailure(call: Call<PokemonRequest>, t: Throwable) {
                Log.e("Pokemkon", "FALLO AL OBTENER POKEMON")
            }

            override fun onResponse(call: Call<PokemonRequest>, response: Response<PokemonRequest>) {
                if (response.isSuccessful){
                    if(offset >= 20){
                        presenter.refreshPokemon(response.body()!!)
                    }else{
                        presenter.pokemonObtained(response.body()!!)
                    }
                    Log.e("Pokemkon", Gson().toJson(response.body()))
                }
            }
        })
    }
}

