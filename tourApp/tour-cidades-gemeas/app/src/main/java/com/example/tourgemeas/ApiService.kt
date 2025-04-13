package com.example.tourgemeas

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

data class UsuarioRequest(
    val email: String,
    val nome: String,
    val senha: String
)

data class LoginRequest(
    val email: String,
    val senha: String
)

data class LoginResponse(
    val usuario: UsuarioResponse
)

data class UsuarioResponse(
    val id: Int,
    val email: String,
    val nome: String
)

interface ApiService {
    @POST("api/users/register")
    suspend fun criarUsuario(@Body usuario: UsuarioRequest): UsuarioResponse

    @POST("api/users/login")
    suspend fun fazerLogin(@Body login: LoginRequest): LoginResponse

    companion object {

        private const val BASE_URL = "http://192.168.1.104:3000/"

        fun create(): ApiService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }



            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
} 