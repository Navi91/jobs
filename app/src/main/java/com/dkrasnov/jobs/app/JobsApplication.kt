package com.dkrasnov.jobs.app

import android.app.Application
import com.dkrasnov.jobs.dagger.ComponentHolder

class JobsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentHolder.initApplicationComponent(this)
    }
}