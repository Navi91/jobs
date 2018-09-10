package com.dkrasnov.jobs.api.search

import com.dkrasnov.jobs.api.retrofit.RetrofitApi
import com.dkrasnov.jobs.model.Vocation
import io.reactivex.Single
import javax.inject.Inject

class SearchApi @Inject constructor(private val retrofitApi: RetrofitApi) {

    fun doGetSearch(query: String, page: Int): Single<List<Vocation>> {
        return retrofitApi.doGetCheckPhone(query, page)
    }
}