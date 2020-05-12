package com.example.basiclogin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basiclogin.user.UserManager

class LoginViewModel(
    private val userManager: UserManager
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun getUserName(): String = userManager.username


    fun login(username: String, password: String) {
        if (userManager.loginUser(username, password)
        ) {
            _loginState.value = LoginViewState.LoginSuccess
        } else {
            _loginState.value = LoginViewState.LoginError("You entered the wrong password")
        }
    }

    fun unRegisterUser() {
        userManager.unRegisterUser()
    }

}