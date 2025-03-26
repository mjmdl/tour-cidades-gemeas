package com.example.aplicativotourgemeas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativotourgemeas.databinding.ActivityLoginBinding
import com.example.aplicativotourgemeas.databinding.ActivityTelaPerfilBinding

class TelaPerfil : AppCompatActivity() {
    private lateinit var telaPerfilAct: ActivityTelaPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaPerfilAct = ActivityTelaPerfilBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(telaPerfilAct.root)

    }
}