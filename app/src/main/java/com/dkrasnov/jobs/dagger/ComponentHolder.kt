package com.dkrasnov.jobs.dagger


import com.dkrasnov.jobs.app.JobsApplication
import com.dkrasnov.jobs.dagger.search.SearchComponent
import com.dkrasnov.jobs.dagger.search.SearchModule

class ComponentHolder {

    companion object {
        private lateinit var applicationComponent: ApplicationComponent
        private var searchComponent: SearchComponent? = null

        fun initApplicationComponent(application: JobsApplication) {

            applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(application)).build()
        }

        fun applicationComponent() = applicationComponent

        @Synchronized
        fun searchComponent(): SearchComponent {
            if (searchComponent == null) {
                searchComponent = applicationComponent.plusSearchComponent(SearchModule())
            }

            return searchComponent ?: throw IllegalStateException()
        }

        @Synchronized
        fun clearSearchComponent() {
            searchComponent = null
        }
    }
}