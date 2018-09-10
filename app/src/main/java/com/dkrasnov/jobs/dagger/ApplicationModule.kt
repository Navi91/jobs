package com.dkrasnov.jobs.dagger

import android.content.Context
import com.dkrasnov.jobs.app.JobsApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: JobsApplication) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideApplication(): JobsApplication = application


}