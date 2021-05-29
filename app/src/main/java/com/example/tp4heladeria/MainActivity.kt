package com.example.tp4heladeria

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp4heladeria.databinding.ActivityMainBinding
import com.example.tp4heladeria.models.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.GoToCliente.setOnClickListener{
            val intent = Intent(this, ClientActivity::class.java);
            startActivity(intent);
        }

        binding.GoToEmpleado.setOnClickListener{
            val intent = Intent(this, LoginEmpleadoActivity::class.java);
            startActivity(intent);
        }

        binding.GoToGerente.setOnClickListener{
            val intent = Intent(this, LoginManagerActivity::class.java);
            startActivity(intent);
        }

    }
}

class MyApplication: Application() {
    companion object {

        val conoName : String = "Cono"
        val vasoName : String = "Vaso de 1/4kg"
        val poteName : String = "Pote de 1kg"

        val Sabores : Array<String> = arrayOf("Chocolate" , "Dulce de leche" , "Vainilla" , "Granizado" , "Fresa" , "Tramontana");

        val Carrito : ArrayList<OrdenDeHelado> = ArrayList<OrdenDeHelado>();



        val Helados :  Array<Helado> = arrayOf<Helado>( Helado(conoName , 2 ,Sabores , null , R.mipmap.helados_de_cono) ,
                Helado(vasoName , 3 , Sabores , arrayOf("Crema de vainilla" , "Chocolate fundido" ) , R.mipmap.helados_de_vaso) ,
                Helado(poteName , 4 , Sabores , arrayOf("Crema de vainilla" , "Chocolate fundido") , R.mipmap.helados_de_pote))


        val Repartidores : ArrayList<Repartidor> = arrayListOf<Repartidor>(Repartidor("Samuel Landaeta" , "9393102" ,null ) ,
                                               Repartidor("Eduardo Lara" , "9334943" , null ) ,
                                               Repartidor("Sebastian Herrero" , "91234332" , null ) ,
                                               Repartidor("Cristo Rey" , "9433678" , null ) )

        val Cajeros = arrayOf<Cajero>(Cajero(0 , 5 ,  ArrayList<OrdenFinal>()),
                                      Cajero(0 , 10 , ArrayList<OrdenFinal>()),
                                      Cajero(0 , 15 , ArrayList<OrdenFinal>()) )

    }

}