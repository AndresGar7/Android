package com.eddigarcia.trabajofinal.heroesapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.eddigarcia.trabajofinal.R
import com.eddigarcia.trabajofinal.databinding.ActivityDetailsHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class DetailsHeroActivity : AppCompatActivity() {

    /** Constante para poder pasar el ID del Heroe para luego usarlo en la siguiente vista*/
    companion object{
        const val EXTRA_HERO_ID = "extra_hero_id"
    }

    private lateinit var binding: ActivityDetailsHeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_HERO_ID).orEmpty()
        extractHeroInformation(id)
    }

    /** Genero nuevo la co-rutina  ya que vamos hacer una peticion a internet ya asi no lograr
     * reventar el codigo. Esto se hace en segundo hilo, no en hilo principal.*/
    private fun extractHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
           val heroDetail = traerRetrofit().create(ApiService::class.java).getHeroDetail(id)

            if (heroDetail.body() != null){
                runOnUiThread { createdUI(heroDetail.body()!!) }
                heroDetail.body()
            }
        }
    }

    private fun createdUI(bodyHero: HeroDetailResponse){
        Picasso.get().load(bodyHero.image.url).into(binding.imgHeroDetail)
        binding.tvHeroNameDetail.text = bodyHero.name
    }
    private fun traerRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}