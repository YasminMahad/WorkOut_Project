package com.yasmin_m.workoutlog.api

import com.yasmin_m.workoutlog.models.LoginRequest
import com.yasmin_m.workoutlog.models.LoginResponse
import com.yasmin_m.workoutlog.models.RegisterRequest
import com.yasmin_m.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
   suspend fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>
}