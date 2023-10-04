/*
 * Copyright (c) 2023.
 *
 * Project name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 3/10/23 20:50
 */

package com.eddigarcia.trabajofinal.heroesapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eddigarcia.trabajofinal.databinding.OnlyOneHeroBinding
import com.squareup.picasso.Picasso

class HeroViewHolder(view: View): RecyclerView.ViewHolder (view){

    private val  binding = OnlyOneHeroBinding.bind(view)
    /** Con este Metodo me encargo de encapsular y pasar los datos
     * que trae la Api a la siguiente vista */
    fun bind(heroItemResponse: HeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvHeroName.text = heroItemResponse.name
        Picasso.get().load(heroItemResponse.heroImage.url).into(binding.imgHero)
        binding.root.setOnClickListener { onItemSelected(heroItemResponse.heroId) }
    }
}