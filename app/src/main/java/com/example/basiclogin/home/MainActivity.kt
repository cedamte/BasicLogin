package com.example.basiclogin.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.basiclogin.R
import com.example.basiclogin.app.BasicLoginApp
import com.example.basiclogin.login.LoginActivity
import com.example.basiclogin.registration.RegistrationActivity
import com.example.basiclogin.user.UserManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userManager: UserManager = (application as BasicLoginApp).userManager

        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            setContentView(R.layout.activity_main)
            homeViewModel = HomeViewModel(userManager = userManager)
            setUpViews()
        }


    }

    private fun setUpViews() {
        btn_logout.setOnClickListener {
            homeViewModel.logout()
            val intent = Intent(
                this,
                LoginActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}
