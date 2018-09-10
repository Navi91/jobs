package com.dkrasnov.jobs.dagger

import com.dkrasnov.jobs.dagger.search.SearchComponent
import com.dkrasnov.jobs.dagger.search.SearchModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, JobsModule::class])
interface ApplicationComponent {

    fun plusSearchComponent(module: SearchModule): SearchComponent
}