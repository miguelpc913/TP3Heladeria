package com.example.tp4heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp4heladeria.databinding.ActivityGerenteBinding
import com.example.tp4heladeria.databinding.ActivityLoginEmpleadoBinding
import com.example.tp4heladeria.models.Repartidor

class LoginEmpleadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginEmpleadoLog.setOnClickListener{
            val DNI : String = binding.LoginDNI.text.toString()
            val name: String = binding.LoginNombreEmpleado.text.toString()

            var repartidor : Repartidor? = MyApplication.Repartidores.find { repartidor -> repartidor.Name == name && repartidor.DNI == DNI }
            if(repartidor !== null){
                val intent = Intent(this, RepartidorActivity::class.java)
                intent.putExtra("repartidor", repartidor)
                startActivity(intent)
            }else{
                Toast.makeText(this , "No se ha encontrado un repartidor con esos datos" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}