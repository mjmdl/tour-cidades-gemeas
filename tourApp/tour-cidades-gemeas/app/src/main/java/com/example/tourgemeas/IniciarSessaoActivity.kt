package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.tourgemeas.databinding.ActivityIniciarSessaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IniciarSessaoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIniciarSessaoBinding
    private val apiService = ApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSessaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        Log.d("IniciarSessaoActivity", "Activity criada")

        binding.btnIniciarSessao.setOnClickListener {
            Log.d("IniciarSessaoActivity", "Botão de login clicado")
            fazerLogin()
        }
    }

    private fun fazerLogin() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        Log.d("IniciarSessaoActivity", "Email: $email, Senha: $senha")

        if (email.isEmpty() || senha.isEmpty()) {
            Log.d("IniciarSessaoActivity", "Campos vazios")
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val loginRequest = LoginRequest(email, senha)
        Log.d("IniciarSessaoActivity", "Tentando fazer login: $loginRequest")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("IniciarSessaoActivity", "Iniciando chamada à API")
                val response = withContext(Dispatchers.IO) {
                    apiService.fazerLogin(loginRequest)
                }
                Log.d("IniciarSessaoActivity", "Resposta da API: $response")
                

                
                Toast.makeText(this@IniciarSessaoActivity, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@IniciarSessaoActivity, TelaPrincipalActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Log.e("IniciarSessaoActivity", "Erro ao fazer login", e)
                e.printStackTrace() // Adiciona stack trace completo
                Toast.makeText(this@IniciarSessaoActivity, "Erro ao fazer login: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}