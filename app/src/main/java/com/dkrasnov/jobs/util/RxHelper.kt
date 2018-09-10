package com.dkrasnov.jobs.util

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Maybe<T>.io(): Maybe<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Single<T>.io(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.io(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.observeMain(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.makeBackground(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

