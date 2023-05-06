package com.example.myapplication.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import com.example.myapplication.R
import com.example.myapplication.services.MyApp
import retrofit2.Call
import retrofit2.Response

class DetailsTiposequiaActivity : AppCompatActivity() {

    private var text = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_tiposequia)
        title = "Detalles de la entidad"
        val parentScroll: NestedScrollView = findViewById(R.id.parent_scroll)
        parentScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, _, _ ->
               // title = "Scroll ($scrollX, $scrollY)"

                if (!v.canScrollVertically(1))
                    Toast.makeText(this, "Final", Toast.LENGTH_SHORT).show()
            })


        val bundle = intent.extras
        val dato = bundle?.getString("id")
        if (dato != null) {
            text = dato.toInt()
        }

        /*     var textView = findViewById<TextView>(R.id.claverest).apply {
                 text = dato
             }*/



        MyApp.getInstance().getApiServices().getElementSequia(text).enqueue(object : retrofit2.Callback<tipoSequiaItem>{
            override fun onResponse(
                call: Call<tipoSequiaItem>,
                response: Response<tipoSequiaItem>
            ) {
                if (response.isSuccessful /*&& !response.body().isNullOrEmpty()*/){
                    Toast.makeText(this@DetailsTiposequiaActivity, "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                    var entidadFedView = findViewById<TextView>(R.id.entidad_fed_view)
                    var MunicipioSequiaView = findViewById<TextView>(R.id.nsequia)
                    var d0View = findViewById<TextView>(R.id.D0_view)
                    var d1View = findViewById<TextView>(R.id.d1_view)
                    var d2View = findViewById<TextView>(R.id.d2_view2)
                    var d3View = findViewById<TextView>(R.id.d3_view)
                    var d4View = findViewById<TextView>(R.id.D4_view)

                    entidadFedView.setText(response.body()?.entidad.toString());
                    MunicipioSequiaView.setText(response.body()?.MunicipiosSequia.toString());
                    d0View.setText(response.body()?.D0.toString());
                    d1View.setText(response.body()?.D1.toString());
                    d2View.setText(response.body()?.D2.toString());
                    d3View.setText(response.body()?.D3.toString());
                    d4View.setText(response.body()?.D4.toString());

                }else{
                    Toast.makeText(this@DetailsTiposequiaActivity, "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<tipoSequiaItem>, t: Throwable) {
                Toast.makeText(this@DetailsTiposequiaActivity, "Error Cargando datos", Toast.LENGTH_LONG).show()

            }

        })
    }
}