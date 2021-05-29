package com.example.tp4heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp4heladeria.databinding.ActivityLoginManagerBinding
import com.example.tp4heladeria.models.Repartidor

class LoginManagerActivity : AppCompatActivity() {
    val password : String = "123";
    val name : String = "Manager"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginManagerLog.setOnClickListener{
            val passwordInput : String = binding.LoginPassword.text.toString()
            val nameInput : String = binding.LoginNombreManager.text.toString()


            if(nameInput !== name && passwordInput == password){
                val intent = Intent(this, GerenteActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this , "Usuario o contraseña incorrecta" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}