package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.viewModel.PokemonViewModel
import com.example.pokemon.viewModel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy{
        findViewById<RecyclerView>(R.id.rvPokemons)
    }

    private val viewModel by lazy{
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemons.observe(this, Observer{
            loadRecyclerView(it)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}