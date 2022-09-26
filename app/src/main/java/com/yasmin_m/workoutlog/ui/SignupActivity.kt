package com.yasmin_m.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yasmin_m.workoutlog.ViewModel.UserViewModel
import com.yasmin_m.workoutlog.databinding.ActivitySignupBinding
import com.yasmin_m.workoutlog.models.RegisterRequest

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.tvSignin.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { RegisterResponse->
            Toast.makeText(baseContext,RegisterResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,LoginActivity::class.java))
            Toast.makeText(baseContext,RegisterResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        })
        userViewModel.regError.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }

    fun validateSignup() {
        var error = false
        binding.tilFirstname.error = null
        binding.tilLastname.error = null
        binding.tilEmails.error = null
        binding.tilPassword.error = null
        binding.tilConfirm.error = null

        var firstname = binding.etFirstname.text.toString()
        if (firstname.isBlank()) {
            binding.tilFirstname.error = "First name is required"
            error = true
        }

        var Lastname = binding.etLastname.text.toString()
        if (Lastname.isBlank()) {
            binding.tilLastname.error = "Last name is required"
            error = true
        }

        var Email = binding.etEmails.text.toString()
        if (Email.isBlank()) {
            binding.tilEmails.error = "Email is required"
            error = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            binding.tilEmails.error= "Not a valid email address"
            error = true
        }

        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }

        var confirm = binding.etConfirm.text.toString()
        if (confirm.isBlank()) {
            binding.tilConfirm.error = "Confirmation is required"
            error = true
        }
        if (password!=confirm){
            binding.tilConfirm.error= "Password does not match"
        }
        if (!error) {
            val registerRequest = RegisterRequest(firstname, Lastname, Email, password, confirm)
            userViewModel.registerUser(registerRequest)
        }
        }
    }