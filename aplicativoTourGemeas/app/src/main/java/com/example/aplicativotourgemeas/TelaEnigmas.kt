package com.example.aplicativotourgemeas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativotourgemeas.databinding.ActivityLoginBinding
import com.example.aplicativotourgemeas.databinding.ActivityTelaEnigmasBinding

class TelaEnigmas : AppCompatActivity() {
    private lateinit var telaEnigmasAct: ActivityTelaEnigmasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaEnigmasAct = ActivityTelaEnigmasBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(telaEnigmasAct.root)

        telaEnigmasAct.btnPerfil.setOnClickListener() {
            val intent = Intent(this, TelaPerfil::class.java)
            startActivity(intent)
        }

        telaEnigmasAct.btnMaps.setOnClickListener() {
            val intent = Intent(this, TelaMapa::class.java)
            startActivity(intent)
        }

        telaEnigmasAct.btnEnigmas.setOnClickListener() {
            val intent = Intent(this, TelaEnigmas::class.java)
            startActivity(intent)
        }
    }
}