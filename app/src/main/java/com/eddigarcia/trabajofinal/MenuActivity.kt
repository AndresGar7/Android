/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.eddigarcia.trabajofinal.firstapp.FirstAppActivity
import com.eddigarcia.trabajofinal.heroesapp.HeroesAppActivity
import com.eddigarcia.trabajofinal.imccalculatorapp.ImcCalculatorAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludoApp = findViewById<Button>(R.id.btnSaludoApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnHeroesApp = findViewById<Button>(R.id.btnHeroesApp)

        btnSaludoApp.setOnClickListener { navigateToSaludoApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnHeroesApp.setOnClickListener { navigateToHeroesApp() }
    }

    // Metodo encargado de mostrar la vista del Saludo App
    private fun navigateToSaludoApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)

    }

    // Metodo encargado de mostrar la vista del IMC App
    private fun navigateToIMCApp() {
        val intent = Intent(this, ImcCalculatorAppActivity::class.java)
        startActivity(intent)
    }

    // Metodo encargado de mostrar la vista del Heroes App
    private fun navigateToHeroesApp() {
        val intent = Intent(this, HeroesAppActivity::class.java)
        startActivity(intent)
    }

}