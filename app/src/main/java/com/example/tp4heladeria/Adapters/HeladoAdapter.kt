package com.example.tp4heladeria.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.MyApplication
import com.example.tp4heladeria.R
import com.example.tp4heladeria.models.Helado
import com.example.tp4heladeria.models.OrdenDeHelado


class HeladoAdapter(val dataSet: Array<Helado>) : RecyclerView.Adapter<HeladoAdapter.ViewHolder>() {

    private class HeladoFormManager(val checkboxes: ArrayList<CheckBox> = ArrayList<CheckBox>(), val type: String, var disabledCheckboxes: Boolean, var limit: Int, val radioButtons: ArrayList<RadioButton> = ArrayList<RadioButton>())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.helado, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val helado : Helado = dataSet[position]
        val heladoFormManager = HeladoFormManager(ArrayList<CheckBox>(), helado.type, false, helado.numberOfOptions as Int, ArrayList<RadioButton>())
        setTextAndImages(holder, helado)
        createCheckboxes(holder, helado.Sabores , heladoFormManager)
        checkboxesEvent(heladoFormManager)
        if (!helado.Optional.isNullOrEmpty()) {
            createOpcionales(holder, helado.Optional , heladoFormManager)
        }
        createAddToCartEvent(holder, helado , heladoFormManager)
    }

    private fun setTextAndImages(holder: ViewHolder, helado: Helado){
        holder.type.text = helado.type
        holder.numeroDeSabores.text = "Sabores (pueden elegir ${helado.numberOfOptions}) : "
        holder.foto.setImageResource(helado.foto)
    }

    private fun createCheckboxes(holder: ViewHolder, sabores : Array<String>, heladoFormManager: HeladoFormManager) {
        sabores.forEachIndexed { index, sabor ->
            val checkBox = CheckBox(holder.checkBoxesSaboresContainer.context)
            checkBox.setText(sabor)
            holder.checkBoxesSaboresContainer.addView(checkBox)
            heladoFormManager.checkboxes.add(checkBox)
        }
    }

    private fun checkboxesEvent(heladoFormManager: HeladoFormManager) {
        heladoFormManager.checkboxes.forEach { checkbox: CheckBox ->
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                var saboresElegidos: Int = heladoFormManager.checkboxes.count { checkBox -> checkBox.isChecked }
                if (saboresElegidos == heladoFormManager.limit && !heladoFormManager.disabledCheckboxes && isChecked) {
                    disableCheckboxes(heladoFormManager)
                } else if (saboresElegidos < heladoFormManager.limit && heladoFormManager.disabledCheckboxes) {
                    enableCheckboxes(heladoFormManager)
                }
            }
        }
    }

    private fun createAddToCartEvent(holder: ViewHolder , helado: Helado , heladoFormManager: HeladoFormManager){
        holder.comprar.setOnClickListener {
            var sabores : ArrayList<String> = ArrayList<String>();
            var opcional : String? = null
            heladoFormManager.checkboxes.forEach { checkbox ->
                if(checkbox.isChecked){
                    sabores.add(checkbox.text.toString())
                    checkbox.isChecked = false
                }
            }

            if(sabores.count() > 0){
                heladoFormManager.radioButtons?.forEach { radioButton ->
                    if(radioButton.isChecked){
                        opcional = radioButton.text.toString()
                        radioButton.isChecked = false
                    }
                }
                var Orden  = OrdenDeHelado(sabores , opcional , helado.type , helado.foto);
                MyApplication.Carrito.add(Orden)
                Toast.makeText(holder.itemView.context ,"${helado.type} se ha añadido al carrito" , Toast.LENGTH_SHORT ).show()
            }else{
                Toast.makeText(holder.itemView.context ,"No se han elegido sabores para ${helado.type}" , Toast.LENGTH_SHORT ).show()
            }
        }
    }

    private fun createOpcionales(holder: ViewHolder, opcionales : Array<String>? , heladoFormManager: HeladoFormManager) {
        holder.opcionales.text = "Opcionales";
        opcionales?.forEach { opcional ->
            val radioButton = RadioButton(holder.heladoRadioOpcionalesContainer.context)
            radioButton.setText(opcional)
            holder.heladoRadioOpcionalesContainer.addView(radioButton)
            heladoFormManager.radioButtons.add(radioButton)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type: TextView
        val numeroDeSabores: TextView
        val foto: ImageView
        val comprar: Button
        val opcionales: TextView
        val checkBoxesSaboresContainer: LinearLayout
        val heladoRadioOpcionalesContainer: RadioGroup

        init {
            foto = view.findViewById(R.id.heladoFoto)
            comprar = view.findViewById(R.id.hi_comprar)
            type = view.findViewById(R.id.heladoTipo)
            numeroDeSabores = view.findViewById(R.id.heladoNumeroSabores)
            opcionales = view.findViewById(R.id.heladoOpcionales)
            checkBoxesSaboresContainer = view.findViewById(R.id.heladoCheckboxSabores)
            heladoRadioOpcionalesContainer = view.findViewById(R.id.heladoRadioOpcionales)
        }
    }


    private fun disableCheckboxes(heladoFormManager: HeladoFormManager) {
        heladoFormManager.checkboxes.forEach { checkbox -> if (!checkbox.isChecked) checkbox.isClickable = false }
        heladoFormManager.disabledCheckboxes = true;
    }

    private fun enableCheckboxes(heladoFormManager: HeladoFormManager) {
        heladoFormManager.checkboxes.forEach { checkbox -> checkbox.isClickable = true }
        heladoFormManager.disabledCheckboxes = false;
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}