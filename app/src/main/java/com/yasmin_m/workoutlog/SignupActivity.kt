package com.yasmin_m.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var tilFirstname: TextInputLayout
    lateinit var etFirstname: TextInputEditText
    lateinit var tilLastname: TextInputLayout
    lateinit var etLastname: TextInputEditText
    lateinit var  tilEmails: TextInputLayout
    lateinit var etEmails: TextInputEditText
    lateinit var tilPassword: TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var tilConfirm: TextInputLayout
    lateinit var etConfirm: TextInputEditText
    lateinit var btnSignup: Button
    lateinit var tvSignin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        castView()

        btnSignup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        tvSignin.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignup.setOnClickListener {
            validateSignup()
        }


    }
    fun castView(){
        tilFirstname= findViewById(R.id.tilFirstname)
        etFirstname = findViewById(R.id.etFirstname)
        tilLastname = findViewById(R.id.tilLastname)
        etLastname = findViewById(R.id.etLastname)
        tilEmails = findViewById(R.id.tilEmails)
        etEmails = findViewById(R.id.etEmails)
        tilPassword = findViewById(R.id.tilPassword)
        etPassword = findViewById(R.id.etPassword)
        tilConfirm = findViewById(R.id.tilConfirm)
        etConfirm = findViewById(R.id.etConfirm)
        btnSignup = findViewById(R.id.btnSignup)
        tvSignin = findViewById(R.id.tvSignin)
    }
    fun validateSignup() {
        var error = false
        tilFirstname.error = null
        tilLastname.error = null
        tilEmails.error = null
        tilPassword.error = null
        tilConfirm.error = null

        var firstname = etFirstname.text.toString()
        if (firstname.isBlank()) {
            tilFirstname.error = "First name is required"
            error = true
        }

        var Lastname = etLastname.text.toString()
        if (Lastname.isBlank()) {
            tilLastname.error = "Last name is required"
            error = true
        }

        var Email = etEmails.text.toString()
        if (Email.isBlank()) {
            tilEmails.error = "Email is required"
            error = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            tilEmails.error= "Not a valid email address"
            error = true
        }

        var password = etPassword.text.toString()
        if (password.isBlank()) {
            tilPassword.error = "Password is required"
            error = true
        }

        var confirm = etConfirm.text.toString()
        if (confirm.isBlank()) {
            tilConfirm.error = "Confirmation is required"
            error = true
        }
        if (password!=confirm){
            tilConfirm.error= "Password does not match"
        }
        if (!error) {
        }
        }
    }