package com.yasmin_m.workoutlog.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasmin_m.workoutlog.Repository.UserRepository
import com.yasmin_m.workoutlog.models.LoginRequest
import com.yasmin_m.workoutlog.models.LoginResponse
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    var regError = MutableLiveData<String>()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
        val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
        loginResponseLiveData.postValue(response.body())
    }
    else{
        regError.postValue(response.errorBody()?.string())  }
    }
    }
}

