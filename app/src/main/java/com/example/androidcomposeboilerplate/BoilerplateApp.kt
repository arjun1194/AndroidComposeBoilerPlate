package com.example.androidcomposeboilerplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BoilerplateApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
