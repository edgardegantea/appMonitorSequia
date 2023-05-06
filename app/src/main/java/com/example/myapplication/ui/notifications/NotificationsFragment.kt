package com.example.myapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.services.MyApp

import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.button3
import kotlinx.android.synthetic.main.fragment_dashboard.editTextTextPersonName3
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Response

class NotificationsFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AdapterDailyForecast.MyViewHolder>? = null
    private var progressBar: ProgressBar? = null
    private val adapterDaily = AdapterDailyForecast()
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding?.progressBar?.visibility ?:  View.VISIBLE

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        MyApp.getInstance().getApiServices().DailyForecast().enqueue(object : retrofit2.Callback<List<DailyForecastItem>>{
            override fun onResponse(
                call: Call<List<DailyForecastItem>>,
                response: Response<List<DailyForecastItem>>
            ) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    Toast.makeText(getContext(), "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                    _binding?.progressBar?.visibility ?:  View.VISIBLE

                    /*       binding?.progressBar?.visibility ?:  View.INVISIBLE
                           binding?.txtsearchDaily?.visibility?: View.VISIBLE
                           binding?.searchDaily?.visibility?: View.VISIBLE*/
                    adapterDaily.setList(response.body() as ArrayList<DailyForecastItem>)
                    RecyclerDayly.apply {
                        // set a LinearLayoutManager to handle Android
                        // RecyclerView behavior
                        layoutManager = LinearLayoutManager(getContext())
                        // set the custom adapter to the RecyclerView
                        adapter = adapterDaily
                    }

                    searchDaily.setOnClickListener{v ->
                        val posibleNumero: String = txtsearchDaily.getText().toString()

                        Toast.makeText(getContext(),posibleNumero, Toast.LENGTH_LONG).show()
                        if(posibleNumero == null){
                            Toast.makeText(getContext(), "busqueda no realizada", Toast.LENGTH_LONG).show()

                        }else{
                            MyApp.getInstance().getApiServices().DailyForecastElement(posibleNumero).enqueue(object : retrofit2.Callback<List<DailyForecastItem>>{
                                override fun onResponse(
                                    call: Call<List<DailyForecastItem>>,
                                    response: Response<List<DailyForecastItem>>
                                ) {
                                    if (response.isSuccessful && !response.body().isNullOrEmpty()){
                                        Toast.makeText(getContext(), "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                                        adapterDaily.setList(response.body() as ArrayList<DailyForecastItem>)

                                        RecyclerDayly.apply {
                                            // set a LinearLayoutManager to handle Android
                                            // RecyclerView behavior
                                            layoutManager = LinearLayoutManager(getContext())
                                            // set the custom adapter to the RecyclerView
                                            adapter = adapterDaily
                                        }


                                    }else{
                                        Toast.makeText(getContext(), "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                                    }
                                }

                                override fun onFailure(call: Call<List<DailyForecastItem>>, t: Throwable) {
                                    Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_LONG).show()

                                }

                            })

                        }

                    }

                }else{
                    Toast.makeText(getContext(), "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<DailyForecastItem>>, t: Throwable) {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_LONG).show()

            }

        })



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}