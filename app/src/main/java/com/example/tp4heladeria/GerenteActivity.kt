package com.example.tp4heladeria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp4heladeria.databinding.ActivityGerenteBinding

class GerenteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGerenteBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.GoToAddRepartidor.setOnClickListener{
            var intent : Intent = Intent(this , AddRepartidorActivity::class.java)
            startActivity(intent)
        }

        binding.GoToHistorial.setOnClickListener{
            var intent : Intent = Intent(this , HistorialDePedidosActivity::class.java)
            startActivity(intent)
        }
    }
}