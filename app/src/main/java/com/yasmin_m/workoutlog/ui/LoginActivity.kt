package com.yasmin_m.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yasmin_m.workoutlog.ViewModel.UserViewModel
import com.yasmin_m.workoutlog.databinding.ActivityLoginBinding
import com.yasmin_m.workoutlog.models.LoginRequest
import com.yasmin_m.workoutlog.models.LoginResponse

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)



    }

    override fun onResume(){
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer {loginResponse->

            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            saveLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.regError.observe(this, Observer{ error->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

    fun castView()  {
        binding.btnLogin.setOnClickListener { validateLogin()
            startActivity(Intent(this,HomeActivity::class.java)) }
        binding.tvSignup.setOnClickListener { startActivity(Intent(this,SignupActivity::class.java)) }
    }

    fun validateLogin(){
        var error = false
        binding.tilPassword.error= null
        binding.tilEmails.error= null
      var email = binding.etEmail.text.toString()
      if (email.isBlank()) {
          binding.tilEmails.error = "Enter email"
          error = true
      }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmails.error = "Not a valid email address"
            error= true
        }
        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Enter password"
            error = true
        }
        if (!error){
            val loginRequest = LoginRequest(email, password)
            binding.pblogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }
    }

    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID", loginResponse.accessToken)
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
}
