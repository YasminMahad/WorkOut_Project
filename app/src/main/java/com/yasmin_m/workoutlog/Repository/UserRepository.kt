package com.yasmin_m.workoutlog.Repository

import com.yasmin_m.workoutlog.api.ApiClient
import com.yasmin_m.workoutlog.api.ApiInterface
import com.yasmin_m.workoutlog.models.LoginRequest
import com.yasmin_m.workoutlog.models.LoginResponse
import com.yasmin_m.workoutlog.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>
    = withContext(Dispatchers.IO){
        val response = apiClient.loginUser(loginRequest)
        return@withContext response
    }


    suspend fun registerUser(registerRequest: RegisterRequest)=
        withContext(Dispatchers.IO) {
            return@withContext apiClient.registerUser(registerRequest)
        }
}