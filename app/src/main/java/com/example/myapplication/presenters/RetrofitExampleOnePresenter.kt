package com.example.myapplication.presenters

import com.example.myapplication.interfaces.RetrofitExampleOne
import com.example.myapplication.models.RetrofitExampleOneModel
import com.example.myapplication.pojos.Pokemon
import com.example.myapplication.pojos.PokemonRequest
import com.example.myapplication.views.RetrofitExampleOneView
import java.util.ArrayList

class RetrofitExampleOnePresenter(val view: RetrofitExampleOneView): RetrofitExampleOne.Presenter {

    val model = RetrofitExampleOneModel(this)
    val allPokemons = ArrayList<Pokemon>()

    override fun getPokemonList(offset: Int) {
        model.getPokemonList(offset)
    }

    override fun pokemonObtained(pokemonRequest: PokemonRequest) {
        view.retrofitPokemonObtained(pokemonRequest.pokemon!!)
    }

    override fun refreshPokemon(pokemonRequest: PokemonRequest) {
        allPokemons.addAll(pokemonRequest.pokemon!!)
        view.refreshPokemon(pokemonRequest.pokemon!!)
    }
}