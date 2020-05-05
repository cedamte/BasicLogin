package com.example.basiclogin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basiclogin.user.UserManager
import timber.log.Timber

class LoginViewModel(
    private val userManager: UserManager
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState


    fun login() {
        if (!userManager.loginUser()) {
            Timber.i("User as been logged in")
            _loginState.value = LoginViewState.LoginSuccess
        } else {
            _loginState.value = LoginViewState.LoginError
        }
    }

}