/*
 * Copyright (c) 2023.
 *
 * Project Name: Trabajo Final
 * Created by Eddi Andres Garcia
 * Copyright (c) 2023. All rights reserved.
 * Last modified 1/10/23 23:05
 */

package com.eddigarcia.trabajofinal.imccalculatorapp
// Librerias necesarias para la funcionalidad de la app
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.eddigarcia.trabajofinal.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorAppActivity : AppCompatActivity() {

    private var isFemaleSelect: Boolean = true
    private var isMaleSelect: Boolean = false
    private var isWeight: Int = 65
    private var isAge: Int = 30
    private var isHeight: Int = 160

    // Declaro las variables de los componentes que voy a utilizar en mi IMCCalculator
    private lateinit var vMale: CardView
    private lateinit var vFemale: CardView
    private lateinit var llyAlt: LinearLayoutCompat
    private lateinit var rsAlt: RangeSlider
    private lateinit var tvAlt: TextView
    private lateinit var tWeight: TextView
    private lateinit var btnSubtractPeso: FloatingActionButton
    private lateinit var btnAddPeso: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnResult: Button
    private lateinit var ctrIMC: ConstraintLayout

    // Constante estatica para compartir datos
    companion object {
        const val KEY_IMC = "imc_result"
        const val KEY_IMC_GEN = "imc_sex"
    }

    // Inicializo los metodos que voy a utilizar al iniciar el IMC
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator_app)
        startComponents()
        startListeners()
        startUI()
    }

    // Inicio todos los componentes que utilizar en la vista IMC
    private fun startComponents() {
        vMale = findViewById(R.id.vMale)
        vFemale = findViewById(R.id.vFemale)
        llyAlt = findViewById(R.id.llyAlt)
        tvAlt = findViewById(R.id.tvAlt)
        rsAlt = findViewById(R.id.rsAlt)
        tWeight = findViewById(R.id.tWeight)
        btnSubtractPeso = findViewById(R.id.btnSubtractPeso)
        btnAddPeso = findViewById(R.id.btnAddPeso)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnResult = findViewById(R.id.btnResult)
        ctrIMC = findViewById(R.id.ctrIMC)
    }

    // Metodo encargado de encapsular las diferentes funciones de cada boton
    private fun startListeners() {
        vMale.setOnClickListener {
            changeRender()
            mySetColor()
        }
        vFemale.setOnClickListener {
            changeRender()
            mySetColor()
        }
        rsAlt.addOnChangeListener { _, value, _ ->
            val decFor = DecimalFormat("#.##")
            val result = decFor.format(value)
            isHeight = value.toInt()
            tvAlt.text = "$result cm"
        }
        btnSubtractPeso.setOnClickListener {
            isWeight -= 1
            setWeight()
        }
        btnAddPeso.setOnClickListener {
            isWeight += 1
            setWeight()
        }
        btnAddAge.setOnClickListener {
            isAge += 1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            isAge -= 1
            setAge()
        }
        btnResult.setOnClickListener {
            val result =  resultIMC()
            var genered = Boolean
            if (isMaleSelect) {
                navigateToResult(result, genered = true)
            }else{
                navigateToResult(result, genered = false)
            }


        }
    }

    // Metodo que cumple la funcion de enviar los datos a la siguiente ventana
    // para mostrar el resultado (Navegacion entre clases).
    private fun navigateToResult(result: Double, genered: Boolean) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(KEY_IMC, result)
        intent.putExtra(KEY_IMC_GEN, genered)
        startActivity(intent)
    }

    // Metodo para calcular el resultado del IMC segun los datos configurados
    private fun resultIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = isWeight/((isHeight.toDouble()/100) * (isHeight.toDouble()/100))
        return df.format(imc).toDouble()
    }

    // Metodo para actualizar la interfaz de la edad
    private fun setAge() {
        tvAge.text = isAge.toString()
    }

    // Metodo para actualizar la interfaz del peso
    private fun setWeight() {
        tWeight.text = isWeight.toString()
    }

    // Metodo para intercambiar el valor de la seleccion entre los botones de Hombre y Mujer
    private fun changeRender() {
        isMaleSelect = !isMaleSelect
        isFemaleSelect = !isFemaleSelect
    }

    // Metodo para cambiar el color del fondo del boton seleccionado
    private fun mySetColor() {
        vMale.setCardBackgroundColor(getBackgroundColor(isMaleSelect))
        vFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelect))

        if (isMaleSelect) {
            btnSubtractAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
            btnAddAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
            btnResult.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
            btnSubtractPeso.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
            btnAddPeso.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.background_button))
            ctrIMC.setBackgroundResource(R.color.purple_200_male)
        }else{
            btnSubtractAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
            btnAddAge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
            btnResult.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
            btnSubtractPeso.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
            btnAddPeso.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200))
            ctrIMC.setBackgroundResource(R.color.teal_200)
        }
    }

    // Metodo que se encarga de cambiar la eleccion del color segun la eleccion del boton
    private fun getBackgroundColor(isSelectComponent: Boolean): Int {
        val colorReference = if (isSelectComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    // Metodo encargado de cargar las funciones al iniciar la interfaz del usuario
    private fun startUI() {
        mySetColor()
        setWeight()
        setAge()
    }

}