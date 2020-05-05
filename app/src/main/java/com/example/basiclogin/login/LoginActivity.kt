package com.example.basiclogin.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.basiclogin.R
import com.example.basiclogin.app.BasicLoginApp
import com.example.basiclogin.home.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = LoginViewModel((application as BasicLoginApp).userManager)
        loginViewModel.loginState.observe(this, Observer { loginState ->
            when (loginState) {
                is LoginViewState.LoginSuccess -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is LoginViewState.LoginError -> {
                    tv_error_message.visibility = View.VISIBLE
                }
            }
        })
        setUpViews()
    }

    private fun setUpViews() {
        btn_login.setOnClickListener {
            loginViewModel.login()
        }
    }
}

sealed class LoginViewState {
    object LoginSuccess : LoginViewState()
    object LoginError : LoginViewState()
}