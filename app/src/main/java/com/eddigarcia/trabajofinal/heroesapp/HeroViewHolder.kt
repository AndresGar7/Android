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
    fun bind(HeroItemResponse: HeroItemResponse){
        binding.tvHeroName.text = HeroItemResponse.name
        binding.imgHero

        Picasso.get().load(HeroItemResponse.heroImage.url).into(binding.imgHero)
    }
}