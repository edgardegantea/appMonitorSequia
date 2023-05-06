package com.example.myapplication.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.home.DetailsTiposequiaActivity
import kotlinx.android.synthetic.main.fragmen_mun_seq.view.*
import kotlinx.android.synthetic.main.fragment_item_sequia.view.*
import java.security.AccessController.getContext

class AdapterMunSeq: RecyclerView.Adapter<AdapterMunSeq.MyViewHolder>() {
    private var list = ArrayList<MunSeqItem>()

    inner class MyViewHolder( parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.fragmen_mun_seq,parent,false)) {


        fun bind(munSeqItem: MunSeqItem) = with(itemView) {
            TXTV_CLAVE.text = munSeqItem.CVE_CONCATENADA.toString()
            TXTVMunseq_Entidad.text = munSeqItem.ENTIDAD
            TXTVMunseq_municipio.text = munSeqItem.NOMBRE_MUN
            TXTVMunseq_Tip_Sequia.text = munSeqItem.TIPOSDESEQUÍA

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      //  holder.itemView.cvb.SetAnimation(AnimationUtils.loadAnimation( getContext(),R.anim.fade_transiction))

        holder.bind(list[position])

        holder.itemView.setOnClickListener { v ->

            val intent = Intent(v.context, DetailsMunSeqActivity::class.java)
            val text: TextView = holder.itemView.findViewById(R.id.TXTV_CLAVE)
            val text2: String = text.getText().toString()

            val message = text.toString()
            intent.putExtra("CVE_CONCATENADA", text2)
            v.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<MunSeqItem>){
        this.list = list
        notifyDataSetChanged()
    }
}