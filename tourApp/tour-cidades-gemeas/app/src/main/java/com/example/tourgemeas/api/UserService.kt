package com.example.tourgemeas.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val admin: Boolean
)

interface UserService {
    @POST("auth/sign-in")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
} 