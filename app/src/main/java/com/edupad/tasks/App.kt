package com.edupad.tasks

import android.app.Application
import com.edupad.tasks.services.Api

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Api.INSTANCE = Api(this)
    }
}
