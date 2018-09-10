package com.dkrasnov.jobs.mvp

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class JobsPresenter<T : JobsView> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected abstract inner class PresenterObserver<T> : Observer<T> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
            addDisposable(d)
        }

        abstract override fun onNext(t: T)

        override fun onError(e: Throwable) {
        }
    }
}