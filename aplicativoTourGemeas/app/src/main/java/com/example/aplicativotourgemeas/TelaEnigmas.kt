package com.example.aplicativotourgemeas

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

    }
}