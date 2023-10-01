/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal.imccalculatorapp
// Librerias necesarias que voy a utilizar en esta vista
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.eddigarcia.trabajofinal.R
import com.eddigarcia.trabajofinal.imccalculatorapp.ImcCalculatorAppActivity.Companion.KEY_IMC
import com.eddigarcia.trabajofinal.imccalculatorapp.ImcCalculatorAppActivity.Companion.KEY_IMC_GEN

class ResultImcActivity : AppCompatActivity() {

    // Creo las variables que voy a utilizar en esta vista
    private lateinit var tvIMCResult: TextView
    private lateinit var tvValueIMC: TextView
    private lateinit var tvSummaryIMC: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var llyResult: LinearLayoutCompat
    private lateinit var ctrResult: ConstraintLayout

    // Inicializo los metodos que voy a utilizar en la parte del resultado del IMC
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        val result = intent.extras?.getDouble(KEY_IMC) ?: -1.0
        val genero = intent.extras?.getBoolean(KEY_IMC_GEN) ?: false

        startComponents()
        startUI(result, genero)
        startListeners()
    }

    // Metodo encargado de validar que boton se presiona para realizar alguna accion
    private fun startListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    // Metodo encargado de realizar los calculos que se van a mostrar en pantalla
    private fun startUI(result: Double, genero: Boolean) {

        tvValueIMC.text = result.toString()

        if (genero){
            llyResult.setBackgroundResource(R.color.purple_200_male)
            ctrResult.setBackgroundResource(R.color.background_component_male)
            btnRecalculate.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
        }else{
            llyResult.setBackgroundResource(R.color.background_component)
            ctrResult.setBackgroundResource(R.color.teal_200)
            btnRecalculate.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
        }

        when(result){
            in 0.00..18.50 -> {
                tvIMCResult.text = getString(R.string.title_peso_bajo)
                tvIMCResult.setTextColor(ContextCompat.getColor(this, R.color.bajo_peso))
                tvSummaryIMC.text = getString(R.string.summary_peso_bajo)
            }
            in 18.51..24.99 -> {
                tvIMCResult.text = getString(R.string.title_normal_peso)
                tvIMCResult.setTextColor(ContextCompat.getColor(this, R.color.normal_peso))
                tvSummaryIMC.text = getString(R.string.summary_normal_bajo)
            }
            in 25.00..29.99 -> {
                tvIMCResult.text = getString(R.string.title_sobrepeso_peso)
                tvIMCResult.setTextColor(ContextCompat.getColor(this, R.color.sobre_peso))
                tvSummaryIMC.text = getString(R.string.summary_sobrepeso_bajo)
            }
            in 30.00..99.99 -> {
                tvIMCResult.text = getString(R.string.title_obecidad)
                tvIMCResult.setTextColor(ContextCompat.getColor(this, R.color.obecidad))
                tvSummaryIMC.text = getString(R.string.summary_obecidad)
            }
            else -> {
                tvIMCResult.text = getString(R.string.error)
                tvIMCResult.setTextColor(ContextCompat.getColor(this, R.color.obecidad))
                tvValueIMC.text = getString(R.string.error)
                tvSummaryIMC.text = getString(R.string.error)
            }
        }
    }

    // Inicializo los componentes que voy a utilizar de la pantalla en variables
    private fun startComponents() {
        tvIMCResult =  findViewById(R.id.tvIMCResult)
        tvValueIMC = findViewById(R.id.tvValueIMC)
        tvSummaryIMC = findViewById(R.id.tvSummaryIMC)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        llyResult = findViewById(R.id.llyResult)
        ctrResult = findViewById(R.id.container_result)
    }
}