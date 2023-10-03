/*
 * Copyright (c) 2023.
 *
 * Project name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 3/10/23 20:47
 */

package com.eddigarcia.trabajofinal.heroesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eddigarcia.trabajofinal.R

class HeroAdapter(var heroesList:List<HeroItemResponse> = emptyList()) :
    RecyclerView.Adapter<HeroViewHolder>() {

    /** Metodo que se encarga de poblar el listado de Heroes */
    fun updateList(list: List<HeroItemResponse>){
        heroesList = list
        notifyDataSetChanged()
    }

    /** Genero la vista en donde voy a mostrar los resultados cuando se obtenga el ID del Heroe */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.only_one_hero, parent, false)
        )
    }

    /** Metodo que se encarga de contar el tamaño los objetos que trae la Api */
    override fun getItemCount() = heroesList.size

    /** Metodo encargado se seleccionar el Heroe */
    override fun onBindViewHolder(viewHolder: HeroViewHolder, position: Int) {
        viewHolder.bind(heroesList[position])
    }

}