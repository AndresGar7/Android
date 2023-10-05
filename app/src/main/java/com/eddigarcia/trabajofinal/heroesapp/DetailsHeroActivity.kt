package com.eddigarcia.trabajofinal.heroesapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.eddigarcia.trabajofinal.databinding.ActivityDetailsHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

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

    /** Metodo con el que inicializo los metodos con los que voy a mostrar los datos en pantalla. */
    private fun createdUI(bodyHero: HeroDetailResponse){
        Picasso.get().load(bodyHero.image.url).into(binding.imgHeroDetail)
        binding.tvHeroNameDetail.text = bodyHero.name
        startStats(bodyHero.power)
        binding.tvRealName.text = bodyHero.biography.fullName.ifEmpty {
            bodyHero.name
        }
        binding.tvPublisher.text = bodyHero.biography.publisher
        binding.tvOcupacion.text = bodyHero.work.work
        binding.tvBase.text = bodyHero.work.ciudad
        binding.tvBirth.text = bodyHero.biography.place_birth
    }

    /** Metodo encargado de pasar los valores de cada habilidad y llamar al metodo que se encarga de
     * procesar las graficas.*/
    private fun startStats(power: HeroPowerResponse) {
        updateHeight(binding.vCombat, power.combat)
        updateHeight(binding.vDurability, power.durability)
        updateHeight(binding.vSpeed, power.speed)
        updateHeight(binding.vStrength, power.strength)
        updateHeight(binding.vIntelligence, power.intelligence)
        updateHeight(binding.vPower, power.power)
    }

    /** Metodo que se encarga de actualizar cada una de las stat de las habilidades de los heroes*/
    private fun updateHeight(view: View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    /** Metodo que se encarga de pasar los px de la pantalla a Dp para configurar bien la medida
     * de la altura de cada stat*/
    private fun pxToDp(px:Float):Int{
        return TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    /** Con esto establezco la conexion a la Api de Super Heroes.*/
    private fun traerRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}