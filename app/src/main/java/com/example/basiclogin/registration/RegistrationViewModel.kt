package com.example.basiclogin.registration


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basiclogin.user.UserManager


private const val MIN_INPUT_LENGTH = 5

class RegistrationViewModel
    (
    private val userManager: UserManager
) : ViewModel() {


    private val _registrationState = MutableLiveData<RegistrationViewState>()
    val registrationState: LiveData<RegistrationViewState>
        get() = _registrationState

    fun registerUser(
        username: String,
        password: String
    ) {
        userManager.registerUser(
            username,
            password
        )
    }

    fun validateInput(
        username: String,
        password: String
    ) {
        when {
            username.length < MIN_INPUT_LENGTH -> _registrationState.value =
                RegistrationViewState.RegistrationError("Username has to be longer than 4 Characters")
            password.length < MIN_INPUT_LENGTH -> _registrationState.value =
                RegistrationViewState.RegistrationError("Password has to be longer than 4 Characters")
            else -> _registrationState.value = RegistrationViewState.RegistrationSuccess
        }
    }
}