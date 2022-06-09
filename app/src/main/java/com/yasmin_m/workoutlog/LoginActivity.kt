package com.yasmin_m.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: TextInputEditText
    lateinit var tilEmail: TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var tilPassword: TextInputLayout
    lateinit var btnLogin: Button
    lateinit var tvSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.etEmail)
        btnLogin = findViewById(R.id.btnLogin)
        tilEmail = findViewById(R.id.tilEmails)
        etPassword = findViewById(R.id.etPassword)
        tilPassword = findViewById(R.id.tilPassword)
        tvSignup = findViewById(R.id.tvSignup)


        tvSignup.setOnClickListener{
            val intent =Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener{
          validateLogin()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
    fun validateLogin(){
        var error = false
        tilPassword.error= null
        tilEmail.error= null
      var email = etEmail.text.toString()
      if (email.isBlank()) {
          tilEmail.error = "Enter email"
          error = true
      }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.error = "Not a valid email address"
            error= true
        }
        var password = etPassword.text.toString()
        if (password.isBlank()) {
            tilPassword.error = "Enter password"
            error = true
        }
        if (!error){}
    }
}
