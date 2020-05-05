package com.example.basiclogin.user

class UserManager {

    val userName: String = "username"
    val userPassword: String = "password"

    var userSession: UserDataSession? = null


    fun isUserLoggedIn(): Boolean {
        return userSession != null
    }

    fun loginUser(): Boolean {
        userSessionStarted()
        return false
    }

    fun logout() {
        userSession = null
    }

    private fun userSessionStarted() {
        userSession = UserDataSession()
    }


}