package com.example.myapplication.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.services.MyApp
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.button3
import kotlinx.android.synthetic.main.fragment_dashboard.editTextTextPersonName3
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Response


class DashboardFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AdapterMunSeq.MyViewHolder>? = null
  //  val editText = R.id.editTextTextPersonName2
    private var _binding: FragmentDashboardBinding? = null
    private val adapterMunSeq = AdapterMunSeq()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)


    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        MyApp.getInstance().getApiServices().Munseq().enqueue(object : retrofit2.Callback<List<MunSeqItem>>{
            override fun onResponse(
                call: Call<List<MunSeqItem>>,
                response: Response<List<MunSeqItem>>
            ) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    Toast.makeText(getContext(), "datos Cargados exitosamente", Toast.LENGTH_LONG).show()
                    adapterMunSeq.setList(response.body() as ArrayList<MunSeqItem>)

                    RecyclerMunSeqTem.apply {
                        // set a LinearLayoutManager to handle Android
                        // RecyclerView behavior
                        layoutManager = LinearLayoutManager(getContext())
                        // set the custom adapter to the RecyclerView
                        adapter = adapterMunSeq
                    }

                        button3.setOnClickListener{v ->
                        val posibleNumero: String = editTextTextPersonName3.getText().toString()

                        Toast.makeText(getContext(),posibleNumero, Toast.LENGTH_LONG).show()
                        if(posibleNumero == null){
                            Toast.makeText(getContext(), "datos Cargados exitosamente", Toast.LENGTH_LONG).show()

                        }else{
                            MyApp.getInstance().getApiServices().Munseqelemt(posibleNumero).enqueue(object : retrofit2.Callback<List<MunSeqItem>>{
                                override fun onResponse(
                                    call: Call<List<MunSeqItem>>,
                                    response: Response<List<MunSeqItem>>
                                ) {
                                    if (response.isSuccessful && !response.body().isNullOrEmpty()){
                                        Toast.makeText(getContext(), "Datos Encontradas", Toast.LENGTH_LONG).show()
                                        adapterMunSeq.setList(response.body() as ArrayList<MunSeqItem>)

                                        RecyclerMunSeqTem.apply {
                                            // set a LinearLayoutManager to handle Android
                                            // RecyclerView behavior
                                            layoutManager = LinearLayoutManager(getContext())
                                            // set the custom adapter to the RecyclerView
                                            adapter = adapterMunSeq
                                        }


                                    }else{
                                        Toast.makeText(getContext(), "Datos no encontrados", Toast.LENGTH_LONG).show()
                                    }
                                }

                                override fun onFailure(call: Call<List<MunSeqItem>>, t: Throwable) {
                                    Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_LONG).show()

                                }

                            })

                        }

                    }

                }else{
                    Toast.makeText(getContext(), "No hay respuesta del servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<MunSeqItem>>, t: Throwable) {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_LONG).show()

            }

        })



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}