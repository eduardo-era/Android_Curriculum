package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.RetrofitExampleOneAdapter
import com.example.myapplication.interfaces.RetrofitExampleOne
import com.example.myapplication.pojos.Pokemon
import com.example.myapplication.presenters.RetrofitExampleOnePresenter
import com.example.myapplication.utils.BaseActivity
import java.util.ArrayList

class RetrofitExampleOneView:BaseActivity(), RetrofitExampleOne.View {

    private val presenter = RetrofitExampleOnePresenter(this)
    private var recyclerRetrofitExampleOne: RecyclerView? = null
    var recharge: Boolean? = null
    var offset: Int? = null
    var adapter: RetrofitExampleOneAdapter? = null
    var layoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_example_one)
        init()
    }

    private fun init(){
        getPokemonList()
        recyclerRetrofitExampleOne = findViewById(R.id.recycler_view)
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, RetrofitExampleOneView::class.java))
    }

    override fun getPokemonList() {
        recharge = true
        offset = 0
        presenter.getPokemonList(0)
        showProgress()
    }

    override fun retrofitPokemonObtained(pokemons: ArrayList<Pokemon>) {
        dismissProgress()
        adapter = RetrofitExampleOneAdapter(this,pokemons)
        layoutManager = GridLayoutManager(this, 3)
        recyclerRetrofitExampleOne!!.layoutManager = layoutManager
        recyclerRetrofitExampleOne!!.adapter = adapter

        recyclerRetrofitExampleOne!!.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    val visibleItemCount = layoutManager!!.childCount
                    val totalItemCount = layoutManager!!.itemCount
                    val pastVisibleItems = layoutManager!!.findFirstVisibleItemPosition()
                    if (recharge!!){
                        if (visibleItemCount + pastVisibleItems >= totalItemCount){
                            recharge = false
                            offset = offset?.plus(20)
                            presenter.getPokemonList(offset!!)
                        }
                    }
                }
            }
        })
    }

    override fun refreshPokemon(pokemons: ArrayList<Pokemon>) {
        recharge = true
        adapter!!.addPokemnsToList(pokemons)
    }
}