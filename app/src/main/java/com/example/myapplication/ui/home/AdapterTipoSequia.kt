package com.example.myapplication.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_item_sequia.view.*


class AdapterTipoSequia : RecyclerView.Adapter<AdapterTipoSequia.MyViewHolder>() {
    private var list = ArrayList<tipoSequiaItem>()

    inner class MyViewHolder( parent: ViewGroup):RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.fragment_item_sequia,parent,false)) {

        fun bind(TipoSequiaItem: tipoSequiaItem) = with(itemView) {
            EntidadFed.text = TipoSequiaItem.entidad
            MunSeq.text = TipoSequiaItem.MunicipiosSequia.toString()
            PorcentajeSequia.text = TipoSequiaItem.porcentajeSequia.toString()
            clave.text = TipoSequiaItem.id.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener { v ->
           val editText = R.id.textView2

             val intent = Intent(v.context, DetailsTiposequiaActivity::class.java)
            val text: TextView = holder.itemView.findViewById(R.id.clave)
            val text2: String = text.getText().toString()

            val message = text.toString()
            intent.putExtra("id", text2)
            v.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<tipoSequiaItem>){
        this.list = list
        notifyDataSetChanged()
    }



}