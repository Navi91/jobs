package com.dkrasnov.jobs.api.search

import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.model.resource.Resource
import com.dkrasnov.jobs.model.resource.Status
import com.dkrasnov.jobs.util.io
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchEngine @Inject constructor(private val searchApi: SearchApi) {

    private val DEBOUNCE_INTERVAL_MILLISECONDS = 200L

    val vocationsObservable = PublishSubject.create<Resource<MutableList<Vocation>>>()

    private val queryObservable = PublishSubject.create<String>()
    private var page = 0
    private var query: String = ""
    private var allPagesLoad = false
    private var vocationsResource: Resource<MutableList<Vocation>> = Resource.success(mutableListOf())

    init {
        queryObservable.debounce(DEBOUNCE_INTERVAL_MILLISECONDS, TimeUnit.MILLISECONDS).subscribe {
            handleQuery(it)
        }

        setAllPagesLoad(false)
    }

    fun setQuery(query: String) {
        queryObservable.onNext(query)
    }

    fun requestNextPage() {
        if (vocationsResource.status == Status.LOADING || query.isEmpty()) return

        if (!allPagesLoad) {
            page++
            setVocationsResource(Resource.loading(vocationsResource.data))
            doSearchRequest()
        }
    }

    private fun handleQuery(query: String) {
        if (this.query == query) return

        page = 0
        this.query = query
        setAllPagesLoad(false)

        if (query.isEmpty()) {
            clearVocations()
            return
        }

        setVocationsResource(Resource.loading(mutableListOf()))

        doSearchRequest()
    }

    private fun doSearchRequest() {
        searchApi.doGetSearch(query, page).io().subscribe(object : SingleObserver<List<Vocation>> {

            override fun onSuccess(t: List<Vocation>) {
                if (t.isEmpty()) {
                    setAllPagesLoad(true)
                    setVocationsResource(Resource.success(vocationsResource.data
                            ?: mutableListOf()))
                } else {
                    addVocations(t)
                }
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                setVocationsResource(Resource.error(e, vocationsResource.data))
            }
        })
    }

    private fun clearVocations() {
        setVocationsResource(Resource.success(mutableListOf()))
    }

    private fun addVocations(vocations: List<Vocation>) {
        val newList = vocationsResource.data ?: mutableListOf()
        newList.addAll(vocations)

        setVocationsResource(Resource.success(newList))
    }

    private fun setVocationsResource(resource: Resource<MutableList<Vocation>>) {
        vocationsResource = resource
        vocationsObservable.onNext(vocationsResource)
    }

    private fun setAllPagesLoad(load: Boolean) {
        allPagesLoad = load
    }
}
