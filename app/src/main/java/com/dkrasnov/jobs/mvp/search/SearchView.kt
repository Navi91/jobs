package com.dkrasnov.jobs.mvp.search

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.mvp.JobsView

interface SearchView : JobsView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgress(progress: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: String?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setVocations(vocations: List<Vocation>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setEmpty(empty: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showVocation(vocation: Vocation)
}