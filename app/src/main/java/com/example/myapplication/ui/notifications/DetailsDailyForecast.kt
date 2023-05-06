package com.example.myapplication.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import com.example.myapplication.R
import com.example.myapplication.services.MyApp
import com.example.myapplication.ui.home.tipoSequiaItem
import retrofit2.Call
import retrofit2.Response

class DetailsDailyForecast : AppCompatActivity() {
    private var text = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_daily_forecast)
        title = "Pronostico"
        /*val parentScroll: NestedScrollView = findViewById(R.id.parent_scroll)
        parentScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, _, _ ->
                // title = "Scroll ($scrollX, $scrollY)"

                if (!v.canScrollVertically(1))
                    Toast.makeText(this, "Final", Toast.LENGTH_SHORT).show()
            })*/


        val bundle = intent.extras
        val dato = bundle?.getString("id")
        if (dato != null) {
            text = dato.toInt()
        }

        /*     var textView = findViewById<TextView>(R.id.claverest).apply {
                 text = dato
             }*/



        MyApp.getInstance().getApiServices().GETDailyForecastElementAPIVIEW(text).enqueue(object : retrofit2.Callback<DailyForecastItem>{
            override fun onResponse(
                call: Call<DailyForecastItem>,
                response: Response<DailyForecastItem>
            ) {
                if (response.isSuccessful /*&& !response.body().isNullOrEmpty()*/){
                    Toast.makeText(this@DetailsDailyForecast, "Datos cargados exitosamente", Toast.LENGTH_LONG).show()
                    var TXTV_desciel = findViewById<TextView>(R.id.TXTV_desciel)
                    var TXTENTIDAD = findViewById<TextView>(R.id.TXTV_ENTIDAD)
                    var MunicipioSequiaView = findViewById<TextView>(R.id.TXTV_MUNICIPIO)
                    var d0View = findViewById<TextView>(R.id.TXTVdloc)
                    var d1View = findViewById<TextView>(R.id.TXTVcc)
                    var d2View = findViewById<TextView>(R.id.TXTVprobpre)
                    var d3View = findViewById<TextView>(R.id.TXTVtemax)
                    var d4View = findViewById<TextView>(R.id.TXTVtemin)

                    TXTV_desciel.setText(response.body()?.desciel.toString());
                    MunicipioSequiaView.setText(response.body()?.nmun.toString());
                    TXTENTIDAD.setText(response.body()?.nes)
                    d0View.setText(response.body()?.dloc.toString());
                    d1View.setText(response.body()?.cc.toString());
                    d2View.setText(response.body()?.probprec.toString());
                    d3View.setText(response.body()?.tmax.toString());
                    d4View.setText(response.body()?.tmin.toString());

                }else{
                    Toast.makeText(this@DetailsDailyForecast, "Datos no encontrados", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<DailyForecastItem>, t: Throwable) {
                Toast.makeText(this@DetailsDailyForecast, "Error Cargando datos", Toast.LENGTH_LONG).show()

            }

        })
    }
}