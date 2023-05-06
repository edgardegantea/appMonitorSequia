package com.example.myapplication.ui.home

import android.content.ContentValues.TAG
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.services.MyApp
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AdapterTipoSequia.MyViewHolder>? = null
    private var _binding: FragmentHomeBinding? = null
    private val adapterTipoSequia = AdapterTipoSequia()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        MyApp.getInstance().getApiServices().clima().enqueue(object : retrofit2.Callback<List<tipoSequiaItem>>{
            override fun onResponse(
                call: Call<List<tipoSequiaItem>>,
                response: Response<List<tipoSequiaItem>>
            ) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    Toast.makeText(getContext(), "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                    adapterTipoSequia.setList(response.body() as ArrayList<tipoSequiaItem>)

                    recyclerView.apply {
                        // set a LinearLayoutManager to handle Android
                        // RecyclerView behavior
                        layoutManager = LinearLayoutManager(getContext())
                        // set the custom adapter to the RecyclerView
                        adapter = adapterTipoSequia
                    }
                }else{
                    Toast.makeText(getContext(), "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<tipoSequiaItem>>, t: Throwable) {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_LONG).show()

            }

        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


