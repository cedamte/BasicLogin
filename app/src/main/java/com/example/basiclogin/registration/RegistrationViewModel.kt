package com.example.basiclogin.registration


import androidx.lifecycle.ViewModel
import com.example.basiclogin.user.UserManager

class RegistrationViewModel
    (
    private val userManager: UserManager
) : ViewModel() {

    fun registerUser(
        username: String,
        password: String
    ) {
        userManager.registerUser(
            username,
            password
        )
    }
}