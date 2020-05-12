package com.example.basiclogin.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.basiclogin.R
import com.example.basiclogin.app.BasicLoginApp
import com.example.basiclogin.home.MainActivity
import com.example.basiclogin.registration.RegistrationActivity
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
                    tv_error_message.text = loginState.errorMessage
                    tv_error_message.visibility = View.VISIBLE
                }
            }
        })
        setUpViews()
    }

    private fun setUpViews() {
        et_username.isEnabled = false
        et_username.setText(loginViewModel.getUserName())

        btn_login.setOnClickListener {
            loginViewModel.login(
                username = et_username.text.toString(),
                password = et_password.text.toString()
            )
        }

        btn_unregister.setOnClickListener {
            loginViewModel.unRegisterUser()
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}

sealed class LoginViewState {
    object LoginSuccess : LoginViewState()
    data class LoginError(val errorMessage: String) : LoginViewState()
}