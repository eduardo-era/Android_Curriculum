package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.pojos.Pokemon
import com.example.myapplication.pojos.PokemonRequest

class RetrofitExampleOneAdapter(val context: Context, val pokemons: ArrayList<Pokemon>): RecyclerView.Adapter<RetrofitExampleOneAdapter.ViewHolder>() {

    private var allPokemon = pokemons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.retrofit_example_one_item_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allPokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = allPokemon[position]
        holder.pokemonName.text = pokemon.name
        val numberPokemon = pokemon.url?.substringAfter("pokemon/")?.substringBefore("/")

        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberPokemon.png")
            .centerCrop()
            .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imagePokemon)
    }

    fun addPokemnsToList(pokemons: ArrayList<Pokemon>){
        allPokemon.addAll(pokemons)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val pokemonName: TextView = itemView.findViewById(R.id.retrofit_exaple_one_text)
        val imagePokemon: ImageView = itemView.findViewById(R.id.retrofit_example_one_image)
    }
}