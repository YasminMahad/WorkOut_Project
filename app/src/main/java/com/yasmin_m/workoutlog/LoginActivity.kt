package com.yasmin_m.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.yasmin_m.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSignup.setOnClickListener{
            val intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener{
          validateLogin()
            startActivity(Intent(this,HomeActivity::class.java))
        }
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
        if (!error){}
    }
}
