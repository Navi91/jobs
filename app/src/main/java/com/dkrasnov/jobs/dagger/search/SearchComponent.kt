package com.dkrasnov.jobs.dagger.search

import com.dkrasnov.jobs.mvp.search.SearchPresenter
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {
    fun inject(searchPresenter: SearchPresenter)
}