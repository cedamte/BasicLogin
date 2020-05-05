package com.example.basiclogin.app

import android.app.Application
import com.example.basiclogin.user.UserManager
import timber.log.Timber

open class BasicLoginApp : Application() {

    open val userManager: UserManager by lazy {
        UserManager()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}