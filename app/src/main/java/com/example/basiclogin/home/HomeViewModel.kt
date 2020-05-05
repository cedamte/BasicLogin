package com.example.basiclogin.home

import androidx.lifecycle.ViewModel
import com.example.basiclogin.user.UserManager

class HomeViewModel(
    private val userManager: UserManager
) : ViewModel() {

    fun logout() {
        userManager.logout()
    }
}