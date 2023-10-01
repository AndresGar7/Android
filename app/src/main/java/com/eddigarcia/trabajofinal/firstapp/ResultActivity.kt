/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.eddigarcia.trabajofinal.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /**
         * Tomo el valor del textView y lo almaceno en el valor tvResult para luego mostrarlo en
         * otra pantalla.
         */
        val tvResult = findViewById<TextView>(R.id.tvResult)

        /***
         * Si hay un valor con Extra_name lo asignara de lo contrario pondra un String vacio
         */
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text = "Hola $name"

    }
}