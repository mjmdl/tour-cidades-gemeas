package com.example.aplicativotourgemeas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativotourgemeas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginAct: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginAct = ActivityLoginBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(loginAct.root)

        loginAct.btnLogin.setOnClickListener() {
            if (loginAct.editUsuario.text.toString() == "admin@email.com"
                && loginAct.editSenha.toString() == "123456") {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                // Aqui você pode chamar a próxima tela com Intent
            } else {
                Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}