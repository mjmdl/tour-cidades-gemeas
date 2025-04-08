package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tourgemeas.databinding.ActivityLoguinBinding

class LoguinActivity : AppCompatActivity() {

    private lateinit var telaLoguinAct: ActivityLoguinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaLoguinAct = ActivityLoguinBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(telaLoguinAct.root)

        telaLoguinAct.btnInicialSessao.setOnClickListener() {
            val intent = Intent(this, TelaPrincipalActivity::class.java)
            startActivity(intent)
        }

    }
}