package com.example.basiclogin.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.basiclogin.R
import com.example.basiclogin.app.BasicLoginApp
import com.example.basiclogin.home.MainActivity
import com.example.basiclogin.user.UserManager
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private val username by lazy {
        findViewById<EditText>(R.id.et_username)
    }
    private val password by lazy {
        findViewById<EditText>(R.id.et_password)
    }

    private val errorMessage: TextView by lazy {
        findViewById<TextView>(R.id.tv_error_message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val userManager: UserManager = (application as BasicLoginApp).userManager
        registrationViewModel = RegistrationViewModel(userManager = userManager)

        registrationViewModel.registrationState.observe(this, Observer { regState ->
            when (regState) {
                is RegistrationViewState.RegistrationSuccess -> {
                    val username = username.text.toString()
                    val password = password.text.toString()
                    registrationViewModel.registerUser(username, password)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is RegistrationViewState.RegistrationError -> {
                    errorMessage.text = regState.errorMessage
                    errorMessage.visibility = View.VISIBLE
                }
            }
        })

        btn_sign_up.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            registrationViewModel.validateInput(username, password)
        }
    }
}

sealed class RegistrationViewState {
    object RegistrationSuccess : RegistrationViewState()
    data class RegistrationError(val errorMessage: String) : RegistrationViewState()
}