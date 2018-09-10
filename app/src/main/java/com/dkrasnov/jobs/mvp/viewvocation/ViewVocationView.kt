package com.dkrasnov.jobs.mvp.viewvocation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.mvp.JobsView

interface ViewVocationView: JobsView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setVocation(vocation: Vocation)
}