package com.dkrasnov.jobs.mvp.viewvocation

import com.arellomobile.mvp.InjectViewState
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.mvp.JobsPresenter

@InjectViewState
class ViewVocationPresenter(private val vocation: Vocation): JobsPresenter<ViewVocationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setVocation(vocation)
    }
}