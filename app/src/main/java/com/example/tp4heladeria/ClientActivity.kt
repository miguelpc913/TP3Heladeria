package com.example.tp4heladeria


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4heladeria.Adapters.HeladoAdapter
import com.example.tp4heladeria.databinding.ActivityClientBinding


class ClientActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityClientBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val recycleViewHelado: RecyclerView = binding.recycleView;

        recycleViewHelado.layoutManager = LinearLayoutManager(this )

        val adapter = HeladoAdapter(MyApplication.Helados);

        recycleViewHelado.adapter = adapter

        binding.checkoutButton.setOnClickListener{
            val intent = Intent(this, CheckoutActivity::class.java);
            startActivity(intent);
        }
    }
}