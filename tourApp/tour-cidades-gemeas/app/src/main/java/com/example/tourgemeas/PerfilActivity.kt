package com.example.tourgemeas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)


        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}