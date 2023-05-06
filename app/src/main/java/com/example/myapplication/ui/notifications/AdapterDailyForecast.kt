package com.example.myapplication.ui.notifications

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_daily_item.view.*

class AdapterDailyForecast : RecyclerView.Adapter<AdapterDailyForecast.MyViewHolder>() {
    private var list = ArrayList<DailyForecastItem>()

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_daily_item, parent, false
        )
    ) {

        fun bind(dailyForecastItem: DailyForecastItem) = with(itemView) {

            textViewDailyCielo.text = dailyForecastItem.desciel
            textViewDailyMUNICIPIO.text = dailyForecastItem.nmun
            textViewDailyPRECIPITACION.text = dailyForecastItem.prec.toString()
            txtvID.text = dailyForecastItem.id.toString()

          /*  EntidadFed.text = dailyForecastItem.entidad
            MunSeq.text = dailyForecastItem.MunicipiosSequia.toString()
            PorcentajeSequia.text = dailyForecastItem.porcentajeSequia.toString()
            clave.text = dailyForecastItem.id.toString()*/


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener { v ->
            val editText = R.id.textView2

            val intent = Intent(v.context, DetailsDailyForecast::class.java)
            val text: TextView = holder.itemView.findViewById(R.id.txtvID)
            val text2: String = text.getText().toString()

            val message = text.toString()
            intent.putExtra("id", text2)
            v.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<DailyForecastItem>) {
        this.list = list
        notifyDataSetChanged()
    }

}