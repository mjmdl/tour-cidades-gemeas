package com.example.tourgemeas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tourgemeas.api.ApiClient
import com.example.tourgemeas.api.LoginRequest
import com.example.tourgemeas.databinding.ActivityIniciarSessaoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class IniciarSessaoActivity : AppCompatActivity() {

    private lateinit var telaInciarSessaoAct: ActivityIniciarSessaoBinding
    private val TAG = "IniciarSessaoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaInciarSessaoAct = ActivityIniciarSessaoBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(telaInciarSessaoAct.root)

        telaInciarSessaoAct.btnIniciarSessao.setOnClickListener {
            val username = telaInciarSessaoAct.editUsuario.text.toString()
            val senha = telaInciarSessaoAct.editSenha.text.toString()

            if (username.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fazerLogin(username, senha)
        }
    }

    private fun fazerLogin(username: String, senha: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "Tentando fazer login com username: $username")
                val loginRequest = LoginRequest(username, senha)
                val response = ApiClient.userService.login(loginRequest)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        Log.d(TAG, "Resposta do login: $loginResponse")
                        
                        if (loginResponse != null) {
                            Toast.makeText(this@IniciarSessaoActivity, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@IniciarSessaoActivity, TelaPrincipalActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@IniciarSessaoActivity, "Erro: Resposta vazia", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@IniciarSessaoActivity, "Erro na resposta: $errorBody", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@IniciarSessaoActivity, "Erro HTTP: ${e.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@IniciarSessaoActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}