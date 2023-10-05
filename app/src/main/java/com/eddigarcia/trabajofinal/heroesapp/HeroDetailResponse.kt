/*
 * Copyright (c) 2023.
 *
 * Project name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 4/10/23 1:28
 */

package com.eddigarcia.trabajofinal.heroesapp

import com.google.gson.annotations.SerializedName

/** Data class que se encarga de obtener todos los datos del Heroe cuando se presiona */
data class HeroDetailResponse (
    @SerializedName("name") val name:String,
    @SerializedName("work") val work: HeroWorkResponse,
    @SerializedName("biography") val biography: HeroBiographyResponse,
    @SerializedName("powerstats") val power: HeroPowerResponse,
    @SerializedName("image") val image: HeroImgDetailResponse
)
/** Con esto clase obtengo los valores del work del  Heroe */
data class HeroWorkResponse(
    @SerializedName("occupation") val work:String,
    @SerializedName("base") val ciudad:String
)

 /** En esta parte obtengo la biografia del Heroe */
data class HeroBiographyResponse (
    @SerializedName("full-name") val fullName:String,
    @SerializedName("publisher") val publisher:String,
    @SerializedName("first-appearance") val appearance:String,
    @SerializedName("place-of-birth") val place_birth:String
)

/** En esta parte obtenemos los valores de las habilidades en las que se destaca cada Heroe */
data class HeroPowerResponse (
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String
)

/** En esta para obtengo la URL de la imagen del Heroe */
data class HeroImgDetailResponse ( @SerializedName("url") val url:String )