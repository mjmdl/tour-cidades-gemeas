package com.example.tourgemeas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tourgemeas.api.ApiClient
import com.example.tourgemeas.databinding.ActivityCriarContaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CriarContaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCriarContaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            binding = ActivityCriarContaBinding.inflate(layoutInflater)
            setContentView(binding.root)

            enableEdgeToEdge()
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.CriarConta.setOnClickListener {
                if (validateFields()) {
                    registerUser()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao inicializar a tela: ${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun validateFields(): Boolean {
        try {
            if (binding.editEnderecoEmail.text.isNullOrEmpty() || 
                binding.editNameUser.text.isNullOrEmpty() || 
                binding.editTextSenha.text.isNullOrEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return false
            }


            val email = binding.editEnderecoEmail.text.toString()
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor, insira um email válido", Toast.LENGTH_SHORT).show()
                return false
            }


            val password = binding.editTextSenha.text.toString()
            if (password.length < 8) {
                Toast.makeText(this, "A senha deve ter no mínimo 8 caracteres", Toast.LENGTH_SHORT).show()
                return false
            }


            val name = binding.editNameUser.text.toString()
            if (name.length < 5) {
                Toast.makeText(this, "O nome deve ter no mínimo 5 caracteres", Toast.LENGTH_SHORT).show()
                return false
            }

            return true
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao validar campos: ${e.message}", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun registerUser() {
        try {
            val email = binding.editEnderecoEmail.text.toString()
            val name = binding.editNameUser.text.toString()
            val password = binding.editTextSenha.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = ApiClient.authService.register(
                        com.example.tourgemeas.api.RegisterRequest(
                            name = name,
                            email = email,
                            password = password
                        )
                    )

                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@CriarContaActivity, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                            val intent = android.content.Intent(this@CriarContaActivity, CuidadoActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val errorMessage = response.errorBody()?.string() ?: response.message()
                            Toast.makeText(this@CriarContaActivity, "Erro ao criar conta: $errorMessage", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@CriarContaActivity, "Erro de conexão: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao registrar usuário: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}