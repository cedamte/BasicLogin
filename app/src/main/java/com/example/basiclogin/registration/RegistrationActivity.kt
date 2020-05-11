package com.example.basiclogin.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basiclogin.R
import com.example.basiclogin.app.BasicLoginApp
import com.example.basiclogin.home.MainActivity
import com.example.basiclogin.user.UserManager
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userManager: UserManager = (application as BasicLoginApp).userManager
        registrationViewModel = RegistrationViewModel(userManager = userManager)

        btn_sign_up.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            registrationViewModel.registerUser(username, password)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}