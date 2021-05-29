package com.example.tp4heladeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.Adapters.CarritoAdapter
import com.example.tp4heladeria.Adapters.OrdenAdapter
import com.example.tp4heladeria.databinding.ActivityHistorialDePedidosBinding
import com.example.tp4heladeria.models.OrdenFinal
import com.example.tp4heladeria.models.Repartidor

class HistorialDePedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHistorialDePedidosBinding.inflate(layoutInflater)
        setContentView(binding.root);

        val recycleViewOrdenes: RecyclerView = binding.recycleViewHistorialDeOrdenes;
        recycleViewOrdenes.layoutManager = LinearLayoutManager(this)
        val allPedidos = arrayListOf<OrdenFinal>();
        MyApplication.Cajeros.forEach { cajero ->
            cajero.Ordenes.forEach { orden ->
                allPedidos.add(orden)
            }
        }
        val adapter = OrdenAdapter(allPedidos);
        recycleViewOrdenes.adapter = adapter

        var topRepartidor : Repartidor? = null;
        var topCounter  = 0;
        MyApplication.Repartidores.forEach { repartidor ->
            var counter = 0;
            allPedidos.forEach { orden -> if(orden.Finished && orden.Repartidor.Name.equals(repartidor.Name) && orden.Repartidor.DNI.equals(repartidor.DNI)) counter ++ }
             if(counter > topCounter){
                 topCounter = counter;
                 topRepartidor = repartidor;
             }else if(counter == topCounter){
                 topRepartidor = null
             }
        }
        if(topRepartidor !== null){
            binding.topRepartidor.text = "Repartidor con mayor numero de pedidos: ${topRepartidor!!.Name} con ${topCounter} pedidos."
        }else{
            binding.topRepartidor.text = "Actualmente no hay un repartidor con mayor numero de pedidos."
        }
    }
}