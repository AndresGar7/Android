/*
 * Copyright (c) 2023.
 *
 * Project name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 3/10/23 17:00
 */

package com.eddigarcia.trabajofinal.heroesapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
/** Implemento la conexion en segundo plano del consumo de datos que voy a realizar. */
interface ApiService {

     /** API para obtener los datos por medio del nombre */
     /** Este es el API que encuentra a todos los super heroes por nombre  e ingresando
      * el Token de seguridad */
     @GET("/api/333063269205584/search/{name}")
     suspend fun getSuperheroes(@Path("name") superheroName:String):Response<HeroDataResponse>

     /** API para obtener los datos por medio del ID. */
     @GET("/api/333063269205584/{id}")
     suspend fun getHeroDetail(@Path("id") heroId:String):Response<HeroDetailResponse>

}