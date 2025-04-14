package com.example.tourgemeas.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
    val admin: Boolean = false
)

data class RegisterResponse(
    val message: String,
    val token: String?
    val id: Long
)

interface AuthService {
    @POST("auth/sign-up")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
} 