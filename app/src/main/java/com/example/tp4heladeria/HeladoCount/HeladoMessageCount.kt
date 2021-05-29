package com.example.tp4heladeria.HeladoCount

import com.example.tp4heladeria.MyApplication
import com.example.tp4heladeria.models.OrdenDeHelado


class HeladoMessageCount (private var conoCount: Int = 0 , private var vasoCount : Int = 0   , private var poteCount : Int = 0) {

    class MessageCount(var helatoTypeMessage: String , var count: String , var heladoType : String)
    var messagesCount: ArrayList<MessageCount> = ArrayList<MessageCount>()


    fun countHelados( helados : ArrayList<OrdenDeHelado>) {
        helados.forEach { helado ->
            when(helado.type){
                MyApplication.conoName -> conoCount ++
                MyApplication.vasoName -> vasoCount ++
                MyApplication.poteName -> poteCount ++
            }
        }
    }

    fun createMessage() {
        val conoMessage : MessageCount = if(conoCount > 0) MessageCount("Conos de Helado", "X${conoCount}", MyApplication.conoName) else MessageCount("", "", MyApplication.conoName)
        val vasoMessage : MessageCount = if(vasoCount > 0) MessageCount("Vasos de Helado", "X${vasoCount}", MyApplication.vasoName) else MessageCount("", "", MyApplication.vasoName)
        val poteMessage : MessageCount = if(poteCount > 0) MessageCount("Potes de Helado", "X${poteCount}", MyApplication.poteName) else MessageCount("", "", MyApplication.poteName)
        messagesCount.add(conoMessage)
        messagesCount.add(vasoMessage)
        messagesCount.add(poteMessage)
    }

}