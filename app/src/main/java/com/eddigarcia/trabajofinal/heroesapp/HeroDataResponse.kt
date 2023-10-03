/*
 * Copyright (c) 2023.
 *
 * Project name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 3/10/23 18:06
 */

package com.eddigarcia.trabajofinal.heroesapp

import com.google.gson.annotations.SerializedName
/** En esta parte obtengo los datos de los super heroes segun su nombre y genero un listado. */
data class HeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val heroes: List<HeroItemResponse>
)
/** Obtengo los datos del heroe que se haya escogido.*/
data class HeroItemResponse(
    @SerializedName("id") val heroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val heroImage:HeroImageResponse,
    @SerializedName("work") val heroBiografy:HeroWorkResponse,
)

// Con esta clase Obtengo el valor de la URL de la Imagen.
data class HeroImageResponse(@SerializedName("url") val url:String)

/** Con esto clase obtengo los valores del work del  Heroe */
data class HeroWorkResponse(
    @SerializedName("occupation") val work:String,
    @SerializedName("base") val ciudad:String
)