package com.dkrasnov.jobs.dagger.search

import com.dkrasnov.jobs.api.search.SearchApi
import com.dkrasnov.jobs.api.retrofit.RetrofitApi
import com.dkrasnov.jobs.api.search.SearchEngine
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun provideSearchApi(retrofitApi: RetrofitApi) = SearchApi(retrofitApi)

    @SearchScope
    @Provides
    fun provideSearchEngine(searchApi: SearchApi) = SearchEngine(searchApi)
}