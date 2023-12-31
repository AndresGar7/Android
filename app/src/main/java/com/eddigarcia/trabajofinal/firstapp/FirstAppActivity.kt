/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal.firstapp
// Cargo las librerias necesarias
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.eddigarcia.trabajofinal.R

class FirstAppActivity : AppCompatActivity() {

    /* Inicializo los metodos que voy a utilizar al iniciar el FirstApp y tambien
    *  me encargo de comprobar y enviar si se encuentra algo escrito.
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener {
            val name = etName.text.toString()

            if (name.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
    }
}