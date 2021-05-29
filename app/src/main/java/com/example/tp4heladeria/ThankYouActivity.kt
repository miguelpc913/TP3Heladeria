package com.example.tp4heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class ThankYouActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)
        var context = this;
        Timer().schedule(2000) {
            val intent = Intent(context, MainActivity::class.java);
            startActivity(intent);
        }
    }
}