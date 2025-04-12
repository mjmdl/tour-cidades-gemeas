package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tourgemeas.databinding.ActivityEnigmaDetalhadoBinding

class EnigmaDetalhadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnigmaDetalhadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnigmaDetalhadoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        val menuHamburguer = findViewById<ImageView>(R.id.menuHamburguer)
        menuHamburguer.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }
}