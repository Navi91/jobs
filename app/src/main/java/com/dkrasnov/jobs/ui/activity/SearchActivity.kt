package com.dkrasnov.jobs.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dkrasnov.jobs.R
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.mvp.search.SearchPresenter
import com.dkrasnov.jobs.mvp.search.SearchView
import com.dkrasnov.jobs.ui.JobsActivity
import com.dkrasnov.jobs.ui.adapter.RecyclerItemClickListener
import com.dkrasnov.jobs.ui.adapter.VocationsAdapter
import com.dkrasnov.jobs.util.gone
import com.dkrasnov.jobs.util.setVisible
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.a_search.*
import rx.Subscription

class SearchActivity : JobsActivity(), SearchView, RecyclerItemClickListener<Vocation> {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    private val vocationsAdapter = VocationsAdapter(this)
    private var editTextSubscription: Subscription? = null

    companion object {
        fun createIntent(context: Context?) = Intent(context, SearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.a_search)

        val layoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = vocationsAdapter
            this.layoutManager = layoutManager
        }

        editTextSubscription = RxTextView.textChanges(searchEditText).map { it.toString() }.subscribe {
            presenter.search(it)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                presenter.onLastVisibleItemChanged(layoutManager.findLastVisibleItemPosition())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        editTextSubscription?.unsubscribe()
    }

    override fun setProgress(progress: Boolean) {
        progressLayout.setVisible(progress)
    }

    override fun setVocations(vocations: List<Vocation>) {
        vocationsAdapter.items = vocations
        vocationsAdapter.notifyDataSetChanged()
    }

    override fun setEmpty(empty: Boolean) {
        nothingFoundTextView.setVisible(empty)
    }

    override fun showVocation(vocation: Vocation) {
        startActivity(ViewVocationActivity.createIntent(this, vocation))
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message
                ?: getString(R.string.error_unknown), Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(item: Vocation, position: Int) {
        presenter.vocationItemClicked(item)
    }


}