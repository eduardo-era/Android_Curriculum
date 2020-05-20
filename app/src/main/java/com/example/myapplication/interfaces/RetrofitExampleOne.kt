package com.example.myapplication.interfaces

import com.example.myapplication.pojos.Pokemon
import com.example.myapplication.pojos.PokemonRequest
import java.util.ArrayList

class RetrofitExampleOne {
    interface View{
        fun getPokemonList()
        fun retrofitPokemonObtained(pokemons: ArrayList<Pokemon>)
        fun refreshPokemon(pokemons: ArrayList<Pokemon>)
    }

    interface Presenter{
        fun getPokemonList(offset:Int)
        fun pokemonObtained(pokemonRequest: PokemonRequest)
        fun refreshPokemon(pokemonRequest: PokemonRequest)
    }

    interface Model{
        fun getPokemonList(offset:Int)
    }
}