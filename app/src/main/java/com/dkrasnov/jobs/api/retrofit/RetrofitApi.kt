package com.dkrasnov.jobs.api.retrofit

import com.dkrasnov.jobs.model.Vocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("positions.json")
    fun doGetCheckPhone(@Query("search") search: String, @Query("page") page: Int): Single<List<Vocation>>
}