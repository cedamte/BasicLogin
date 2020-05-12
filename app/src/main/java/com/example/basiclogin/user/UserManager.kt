package com.example.basiclogin.user

import com.example.basiclogin.storage.Storage

private const val REGISTERED_USER = "registered_user"
private const val PASSWORD_SUFFIX = "password"

class UserManager(private val storage: Storage) {

    private var userSession: UserDataSession? = null

    fun registerUser(username: String, password: String) {
        storage.setString(REGISTERED_USER, username)
        storage.setString("$username$PASSWORD_SUFFIX", password)
        userSessionStarted()
    }

    val username: String
        get() = storage.getString(REGISTERED_USER)

    fun isUserRegistered(): Boolean = storage.getString(REGISTERED_USER).isNotEmpty()


    fun isUserLoggedIn(): Boolean {
        return userSession != null
    }

    fun loginUser(
        username: String,
        password: String
    ): Boolean {
        val registeredUser = this.username
        if (registeredUser != username) return false

        val registeredPassword =
            storage.getString("$username$PASSWORD_SUFFIX")
        if (registeredPassword != password) return false

        userSessionStarted()
        return true
    }

    fun logout() {
        userSession = null
    }

    private fun userSessionStarted() {
        userSession = UserDataSession()
    }

    fun unRegisterUser() {
        val username = storage.getString(REGISTERED_USER)
        storage.setString(REGISTERED_USER, "")
        storage.setString("$username$PASSWORD_SUFFIX", "")
        logout()
    }


}