package com.example.tp4heladeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp4heladeria.databinding.ActivityAddRepartidorBinding
import com.example.tp4heladeria.models.Repartidor

class AddRepartidorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddRepartidorBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.AddRepartidor.setOnClickListener{
            var name : String = binding.AddInputName.text.toString()
            var dni : String = binding.AddInputDNI.text.toString()

            if(name.isNotBlank() && dni.isNotBlank()){
                var newRepartidor = Repartidor(name , dni ,null );
                MyApplication.Repartidores.add(newRepartidor);
                binding.AddInputName.text.clear()
                binding.AddInputDNI.text.clear()
            }else{
                Toast.makeText(this , "Por favor revise los campos" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}