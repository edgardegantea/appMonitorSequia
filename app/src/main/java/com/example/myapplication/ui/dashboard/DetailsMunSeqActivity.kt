package com.example.myapplication.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import com.example.myapplication.R
import com.example.myapplication.services.MyApp
import retrofit2.Call
import retrofit2.Response

class DetailsMunSeqActivity : AppCompatActivity() {

    private var text = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_mun_seq)

        title = "Detalles del Municipio"
        val parentScroll: NestedScrollView = findViewById(R.id.parent_scroll)
        parentScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, _, _ ->
                // title = "Scroll ($scrollX, $scrollY)"

                if (!v.canScrollVertically(1))
                    Toast.makeText(this, "Final", Toast.LENGTH_SHORT).show()
            })


        val bundle = intent.extras
        val dato = bundle?.getString("CVE_CONCATENADA")

        if (dato != null) {
            text = dato.toInt()
        }

        /*     var textView = findViewById<TextView>(R.id.claverest).apply {
                 text = dato
             }*/



        MyApp.getInstance().getApiServices().getElementSequiaDetails(text).enqueue(object : retrofit2.Callback<MunSeqItem>{
            override fun onResponse(
                call: Call<MunSeqItem>,
                response: Response<MunSeqItem>
            ) {
                if (response.isSuccessful /*&& !response.body().isNullOrEmpty()*/){
                    Toast.makeText(this@DetailsMunSeqActivity, "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                    var entidad = findViewById<TextView>(R.id.textView_entidad_munseq)
                    var municipio = findViewById<TextView>(R.id.TXTV_MUNSEQ_NOMBRE_MUN)
                    var concatenada = findViewById<TextView>(R.id.TXTV_MUNSEQ_CONCATENADA)
                    var orgcuenca = findViewById<TextView>(R.id.TXTV_MUNSEQ_ORG_CUENCA)
                    var concuenca = findViewById<TextView>(R.id.TXTV_MUNSEQ_CON_CUENCA)
                    var tiposequia = findViewById<TextView>(R.id.TXTV_MUNSEQ_TIPOSDESEQUIA)
                    var clave = findViewById<TextView>(R.id.TXTV_MUNSEQ_CLAVE)

                    entidad.setText(response.body()?.ENTIDAD.toString());
                    municipio.setText(response.body()?.NOMBRE_MUN.toString());
                    concatenada.setText(response.body()?.CVE_CONCATENADA.toString());
                    orgcuenca.setText(response.body()?.ORG_CUENCA.toString());
                    concuenca.setText(response.body()?.CON_cuenca.toString());
                    tiposequia.setText(response.body()?.TIPOSDESEQUÍA.toString());
                    clave.setText(response.body()?.CLAVE.toString());

                }else{
                    Toast.makeText(this@DetailsMunSeqActivity, "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MunSeqItem>, t: Throwable) {
                Toast.makeText(this@DetailsMunSeqActivity, "Error Cargando datos", Toast.LENGTH_LONG).show()

            }

        })
    }

}