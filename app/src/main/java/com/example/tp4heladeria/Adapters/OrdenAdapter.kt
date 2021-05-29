package com.example.tp4heladeria.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.HeladoCount.HeladoMessageCount
import com.example.tp4heladeria.MyApplication
import com.example.tp4heladeria.R
import com.example.tp4heladeria.models.OrdenFinal


class OrdenAdapter(val dataSet: ArrayList<OrdenFinal>) : RecyclerView.Adapter<OrdenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.orden_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ordenDeHelado : OrdenFinal = dataSet[position]
        val heladoMessageCount = HeladoMessageCount();
        heladoMessageCount.countHelados(ordenDeHelado.Helados);
        heladoMessageCount.createMessage();
        setText(holder , ordenDeHelado ,  heladoMessageCount)
    }

    private fun setText(holder: ViewHolder, ordenDeHelado : OrdenFinal , heladoMessageCount: HeladoMessageCount){
        holder.repartidor.text = ordenDeHelado.Repartidor.Name;
        holder.status.text = if(ordenDeHelado.Finished) "Completada" else "En proceso";
        heladoMessageCount.messagesCount.forEach { heladoMessage ->
            when(heladoMessage.heladoType){
                MyApplication.conoName -> {
                    holder.conoMessage.text = heladoMessage.helatoTypeMessage
                    holder.conoCount.text = heladoMessage.count
                }
                MyApplication.vasoName -> {
                    holder.vasoMessage.text = heladoMessage.helatoTypeMessage
                    holder.vasoCount.text = heladoMessage.count
                }
                MyApplication.poteName -> {
                    holder.poteMessage.text  = heladoMessage.helatoTypeMessage
                    holder.poteCount.text = heladoMessage.count
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repartidor : TextView
        val status : TextView
        val conoCount : TextView
        val conoMessage : TextView
        val vasoMessage : TextView
        val vasoCount : TextView
        val poteMessage : TextView
        val poteCount : TextView

        init {
            repartidor = view.findViewById(R.id.OrdenRepartidor)
            status = view.findViewById(R.id.ordenCompletada)
            conoCount = view.findViewById(R.id.ordenConoCount)
            conoMessage = view.findViewById(R.id.ordenConoMessage)
            vasoCount = view.findViewById(R.id.ordenVasoCount)
            vasoMessage = view.findViewById(R.id.ordenVasoMessage)
            poteCount = view.findViewById(R.id.ordenPoteCount)
            poteMessage = view.findViewById(R.id.ordenPoteMessage)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}