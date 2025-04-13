package com.example.tourgemeas.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String,
    val token: String?
)

interface AuthService {
    @POST("auth/sign-up")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://tour-cidades-gemeas-production.up.railway.app/"

    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
} 