package com.example.tp4heladeria.models

import java.io.Serializable

data class OrdenDeHelado (val Sabores: ArrayList<String>, val  Optional: String? , val type : String , val foto: Int) : Serializable
data class OrdenFinal (val Helados : ArrayList<OrdenDeHelado> , val Repartidor : Repartidor , var Finished : Boolean) : Serializable
