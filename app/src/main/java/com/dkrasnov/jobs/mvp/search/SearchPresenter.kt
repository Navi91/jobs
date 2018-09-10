package com.dkrasnov.jobs.mvp.search

import com.arellomobile.mvp.InjectViewState
import com.dkrasnov.jobs.api.search.SearchEngine
import com.dkrasnov.jobs.dagger.ComponentHolder
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.model.resource.Resource
import com.dkrasnov.jobs.model.resource.Status
import com.dkrasnov.jobs.mvp.JobsPresenter
import com.dkrasnov.jobs.util.observeMain
import javax.inject.Inject

@InjectViewState
class SearchPresenter : JobsPresenter<SearchView>() {

    private val VISIBLE_THRESHOLD = 1

    @Inject
    lateinit var searchEngine: SearchEngine

    private var vocationsTotalCount = 0

    init {
        ComponentHolder.searchComponent().inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        searchEngine.vocationsObservable.observeMain().subscribe(object : PresenterObserver<Resource<MutableList<Vocation>>>() {
            override fun onNext(t: Resource<MutableList<Vocation>>) {
                when (t.status) {
                    Status.LOADING -> {
                        setProgress(true)

                        viewState.setEmpty(false)
                        viewState.setVocations(t.data ?: listOf())
                    }
                    Status.ERROR -> {
                        setProgress(false)
                        showError(t.error?.message)
                        updateTotalCount(t.data)
                    }
                    Status.SUCCESS -> {
                        setProgress(false)
                        updateTotalCount(t.data)
                        viewState.setVocations(t.data ?: listOf())
                        viewState.setEmpty(t.data?.isEmpty() ?: false)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        ComponentHolder.clearSearchComponent()
    }

    fun search(query: String) {
        searchEngine.setQuery(query)
    }

    fun onLastVisibleItemChanged(lastVisibleItem: Int) {
        if (vocationsTotalCount <= lastVisibleItem + VISIBLE_THRESHOLD) {
            searchEngine.requestNextPage()
        }
    }

    fun vocationItemClicked(vocation: Vocation) {
        viewState.showVocation(vocation)
    }

    private fun updateTotalCount(vocations: List<Vocation>?) {
        vocationsTotalCount = vocations?.size ?: 0
    }

    private fun setProgress(progress: Boolean) {
        viewState.setProgress(progress)
    }

    private fun showError(message: String?) {
        viewState.showError(message)
    }
}