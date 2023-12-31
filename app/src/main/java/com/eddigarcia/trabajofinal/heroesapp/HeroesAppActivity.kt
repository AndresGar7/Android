/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal.heroesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddigarcia.trabajofinal.databinding.ActivityHeroesAppBinding
import com.eddigarcia.trabajofinal.heroesapp.DetailsHeroActivity.Companion.EXTRA_HERO_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesAppActivity : AppCompatActivity() {
    /** Inicializo los espacios de memoria para las variables  con las que voy a trabajar. */
    private lateinit var binding: ActivityHeroesAppBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: HeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = traerRetrofit()
        startUI()
    }

    /** Metodo encargado de iniciar con Binding lo que se realizaba anteriormente con startComponents */
    private fun startUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                buscarPorNombre(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean { return false }
        })

        /** Cargo la variables con los datos de la busqueda de la Api Principal y luego
         * poder pasar estos datos a la siguiente Api de Detalle*/

        adapter = HeroAdapter{ heroId -> navigateToDetailHero(heroId)}
        binding.searchView.queryHint = "Buscar Super Heroe"
        binding.rvSearchHero.setHasFixedSize(true)
        binding.rvSearchHero.layoutManager = LinearLayoutManager(this)
        binding.rvSearchHero.adapter = adapter
    }

    /** Metodo para buscar por medio de corrutinas en un hilo secundario  (IO) el nombre de los heroes */
    private fun buscarPorNombre(query: String) {
        binding.proBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<HeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful){
                val response: HeroDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.heroes)
                        binding.proBar.isVisible = false
                    }
                }
            }else{
                Log.i("GARCIA", "NoFunciona :(")
            }
        }
    }

    /** Implemento Retrofit en esta sección para trabajar de una manera mucho mas rapida y facil. */
    private fun traerRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Metodo para poder navegar hacia mi otra pantalla de Detalles de los Heroes */
    private fun navigateToDetailHero(id: String) {
        val intent = Intent(this,DetailsHeroActivity::class.java)
        intent.putExtra(EXTRA_HERO_ID,id)
        startActivity(intent)
    }
}