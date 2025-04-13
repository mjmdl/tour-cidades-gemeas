package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.tourgemeas.databinding.ActivityCriarContaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CriarContaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCriarContaBinding
    private val apiService = ApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriarContaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.btnCriarConta.setOnClickListener {
            criarConta()
        }
    }

    private fun criarConta() {
        val email = binding.editEmail.text.toString()
        val nome = binding.editNome.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isEmpty() || nome.isEmpty() || senha.isEmpty() ) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }



        val usuario = UsuarioRequest(email, nome, senha)
        Log.d("CriarConta", "Tentando criar usuário: $usuario")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                Log.d("CriarConta", "Iniciando chamada à API")
                val response = withContext(Dispatchers.IO) {
                    apiService.criarUsuario(usuario)
                }
                Log.d("CriarConta", "Resposta da API: $response")
                Toast.makeText(this@CriarContaActivity, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@CriarContaActivity, TelaPrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Log.e("CriarConta", "Erro ao criar conta", e)
                Toast.makeText(this@CriarContaActivity, "Erro ao criar conta: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}