package com.example.tp4heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.Adapters.CarritoAdapter
import com.example.tp4heladeria.databinding.ActivityCheckoutBinding
import com.example.tp4heladeria.models.Cajero
import com.example.tp4heladeria.models.OrdenDeHelado
import com.example.tp4heladeria.models.OrdenFinal
import com.example.tp4heladeria.models.Repartidor

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckoutBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val recycleViewOrdenes: RecyclerView = binding.recycleViewOrdenes;
        recycleViewOrdenes.layoutManager = LinearLayoutManager(this)
        val adapter = CarritoAdapter(MyApplication.Carrito);
        recycleViewOrdenes.adapter = adapter

        binding.confirmarCompra.setOnClickListener {
            finalizeCheckout();
            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }
    }

    private fun finalizeCheckout() {
        var cajero: Cajero? = MyApplication.Cajeros.find { cajero -> cajero.Ordenes.count() < cajero.RequestLimit }
        var repartidor: Repartidor? = MyApplication.Repartidores.find { repartidor -> repartidor.OrdenActual == null }
        if (cajero !== null && repartidor !== null) {
            assignOrder(cajero, repartidor)
        }else{
            cancelOrder(cajero, repartidor)
        }
    }

    private fun assignOrder(cajero : Cajero , repartidor: Repartidor) {
        var orden = OrdenFinal(MyApplication.Carrito.toMutableList() as ArrayList<OrdenDeHelado>, repartidor, false);
        repartidor.OrdenActual = orden;
        cajero.Ordenes.add(orden)
        MyApplication.Carrito.clear();
    }

    private fun cancelOrder(cajero : Cajero? , repartidor: Repartidor?) {
        if (cajero === null ) {
            Toast.makeText(this ,"Actualmente no hay cajeros disponibles, por favor vuelva otro dia, disculpe la molestia" , Toast.LENGTH_SHORT ).show()
        }
        if (repartidor === null) {
            Toast.makeText(this ,"Actualmente no hay repartidores disponibles, por favor vuelva mas tarde" , Toast.LENGTH_SHORT ).show()
        }

    }
}
