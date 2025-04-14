package com.example.tourgemeas

import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        telaInciarSessaoAct = ActivityIniciarSessaoBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        enableEdgeToEdge()
        setContentView(telaInciarSessaoAct.root)

        telaInciarSessaoAct.btnIniciarSessao.setOnClickListener {
            val username = telaInciarSessaoAct.editUsuario.text.toString()
            val senha = telaInciarSessaoAct.editSenha.text.toString()

            if (username.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login via API
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
                            // Salvar informações do usuário
                            sharedPreferences.edit().apply {
                                putString("token", loginResponse.token)
                                putBoolean("Admin", loginResponse.admin)
                                apply()
                            }

                            Log.d(TAG, "Login bem-sucedido. Admin: ${loginResponse.admin}")
                            
                            // Redirecionar para a tela apropriada
                            val intent = if (loginResponse.admin) {
                                Log.d(TAG, "Redirecionando para TelaAdminActivity")
                                Intent(this@IniciarSessaoActivity, TelaAdminActivity::class.java)
                            } else {
                                Log.d(TAG, "Redirecionando para TelaPrincipalActivity")
                                Intent(this@IniciarSessaoActivity, TelaPrincipalActivity::class.java)
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@IniciarSessaoActivity, "Erro: Resposta vazia", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e(TAG, "Erro na resposta: $errorBody")
                        Toast.makeText(this@IniciarSessaoActivity, "Erro na resposta: $errorBody", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: HttpException) {
                Log.e(TAG, "Erro HTTP: ${e.code()}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@IniciarSessaoActivity, "Erro HTTP: ${e.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erro: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@IniciarSessaoActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}