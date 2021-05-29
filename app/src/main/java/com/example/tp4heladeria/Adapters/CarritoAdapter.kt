package com.example.tp4heladeria.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.MyApplication
import com.example.tp4heladeria.R
import com.example.tp4heladeria.models.OrdenDeHelado

class CarritoAdapter(val dataSet: ArrayList<OrdenDeHelado>) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ordenDeHelado : OrdenDeHelado = dataSet[position]
        holder.remover.setOnClickListener{
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
        }
        setTextAndImages(holder , ordenDeHelado)

    }

    private fun setTextAndImages(holder: ViewHolder, ordenDeHelado: OrdenDeHelado){
        holder.type.text = ordenDeHelado.type
        holder.sabores.text = createSaboresText(ordenDeHelado.Sabores)
        holder.opcionales.text = createOpcionalesText(ordenDeHelado.Optional)
        holder.foto.setImageResource(ordenDeHelado.foto);

    }

    private fun createOpcionalesText(optional : String?) : String{
        return if(!optional.isNullOrEmpty()) "Opcional: $optional" else ""
    }

    private fun createSaboresText(sabores :  ArrayList<String>) : String{
        var saboresText : String = ""
        var lastPosition : Int = sabores.count() - 1
        sabores.forEachIndexed{ index , sabor  ->
            when(index){
                0 -> saboresText += "Sabores: $sabor "
                lastPosition -> saboresText += " y $sabor"
                else -> saboresText += ", $sabor"
            }
        }
        return saboresText;
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type: TextView
        val sabores: TextView
        val opcionales: TextView
        val foto: ImageView
        val remover: Button
        init {
            sabores = view.findViewById(R.id.OrdenHeladoSabores)
            foto = view.findViewById(R.id.OrdenheladoFoto)
            remover = view.findViewById(R.id.OrdenHeladoRemover)
            type = view.findViewById(R.id.OrdenheladoTipo)
            opcionales = view.findViewById(R.id.OrdenHeladoOpcionales)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}





