package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tourgemeas.databinding.ActivityIniciarSessaoBinding
import com.example.tourgemeas.databinding.ActivityLoguinBinding

class IniciarSessaoActivity : AppCompatActivity() {

    private lateinit var telaInciarSessaoAct: ActivityIniciarSessaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaInciarSessaoAct = ActivityIniciarSessaoBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(telaInciarSessaoAct.root)


        telaInciarSessaoAct.btnIniciarSessao.setOnClickListener() {
            if (telaInciarSessaoAct.editUsuario.text.toString() == "admin@email.com"
                && telaInciarSessaoAct.editSenha.text.toString() == "123456") {

                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, TelaPrincipalActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}