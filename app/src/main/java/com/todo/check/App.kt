package com.todo.check

import android.app.Application

class App : Application() {
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        prefs =
            PreferenceUtil(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var INSTANCE: App
        lateinit var prefs: PreferenceUtil
    }
}