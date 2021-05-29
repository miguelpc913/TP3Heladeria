package com.example.tp4heladeria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tp4heladeria.HeladoCount.HeladoMessageCount
import com.example.tp4heladeria.databinding.ActivityRepartidorBinding
import com.example.tp4heladeria.models.Repartidor

class RepartidorActivity : AppCompatActivity() {

    private lateinit var repartidor : Repartidor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRepartidorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent
        repartidor =  i.getSerializableExtra("repartidor") as Repartidor

        if(repartidor.OrdenActual !== null){
            withOrderStatus(binding)
        }else{
            noOrderStatus(binding)
        }
    }

    private fun noOrderStatus(binding: ActivityRepartidorBinding) {
        binding.repartidorStatus.text = "Actualmente no tienes pedidos"
        binding.repartidorEntregarOrden.isVisible = false
        binding.repartidorEntregarOrden.isClickable = false
        binding.conoMessage.text = ""
        binding.conoCount.text = ""
        binding.vasoMessage.text = ""
        binding.vasoCount.text = ""
        binding.poteMessage.text = ""
        binding.poteCount.text = ""
    }

    private fun withOrderStatus(binding: ActivityRepartidorBinding) {
        binding.repartidorStatus.text = "Actualmente tienes pedidos"
        var heladoCounter =  HeladoMessageCount();
        heladoCounter.countHelados(repartidor.OrdenActual!!.Helados);
        heladoCounter.createMessage();
        addTextToLayout(binding , heladoCounter)
        binding.repartidorEntregarOrden.setOnClickListener{
            clearOrder()
            noOrderStatus(binding)
        }
    }

    private fun clearOrder(){
        repartidor = MyApplication.Repartidores.find { repartidorOriginal ->  repartidorOriginal.Name.equals(repartidor.Name) && repartidorOriginal.DNI.equals(repartidor.DNI) }!!
        repartidor.OrdenActual!!.Finished = true
        repartidor.OrdenActual = null
    }

    private fun addTextToLayout(binding: ActivityRepartidorBinding , heladoMessageCount: HeladoMessageCount){
        heladoMessageCount.messagesCount.forEach { heladoMessage ->
            when(heladoMessage.heladoType){
                MyApplication.conoName -> {
                    binding.conoMessage.text = heladoMessage.helatoTypeMessage
                    binding.conoCount.text = heladoMessage.count
                }
                MyApplication.vasoName -> {
                    binding.vasoMessage.text = heladoMessage.helatoTypeMessage
                    binding.vasoCount.text = heladoMessage.count
                }
                MyApplication.poteName -> {
                    binding.poteMessage.text  = heladoMessage.helatoTypeMessage
                    binding.poteCount.text = heladoMessage.count
                }
            }
        }
    }


}