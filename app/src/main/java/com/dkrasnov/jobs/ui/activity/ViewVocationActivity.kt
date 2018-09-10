package com.dkrasnov.jobs.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.dkrasnov.jobs.R
import com.dkrasnov.jobs.api.glide.GlideApp
import com.dkrasnov.jobs.model.Vocation
import com.dkrasnov.jobs.mvp.viewvocation.ViewVocationPresenter
import com.dkrasnov.jobs.mvp.viewvocation.ViewVocationView
import com.dkrasnov.jobs.ui.JobsActivity
import com.dkrasnov.jobs.util.gone
import com.dkrasnov.jobs.util.setHtmlCompat
import com.dkrasnov.jobs.util.setVisible
import com.dkrasnov.jobs.util.visible
import kotlinx.android.synthetic.main.a_view_vocation.*

class ViewVocationActivity : JobsActivity(), ViewVocationView {

    @InjectPresenter
    lateinit var presenter: ViewVocationPresenter

    @ProvidePresenter
    fun provideViewVocationPresenter(): ViewVocationPresenter {
        return ViewVocationPresenter(intent.getParcelableExtra(VOCATION_EXTRA) as Vocation)
    }

    companion object {

        private val VOCATION_EXTRA = "vocation_extra"

        fun createIntent(context: Context?, vocation: Vocation): Intent {
            return Intent(context, ViewVocationActivity::class.java).apply {
                putExtra(VOCATION_EXTRA, vocation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.a_view_vocation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setVocation(vocation: Vocation) {
        supportActionBar?.title = vocation.company

        GlideApp.with(this)
                .load(vocation.companyLogo)
                .placeholder(R.drawable.ic_image_gray_24dp)
                .downsample(DownsampleStrategy.AT_MOST)
                .into(logoImageView)

        titleTextView.text = vocation.title
        locationTextView.text = "${vocation.type} / ${vocation.location}"

        setHtmlToViewOrHide(descriptionTextView, vocation.description)
        setHtmlToViewOrHide(companyUrlTextView, vocation.companyUrl)
    }

    private fun setHtmlToViewOrHide(textView: TextView, html: String?) {
        if (TextUtils.isEmpty(html)) {
            textView.gone()
        } else {
            textView.visible()
            textView.setHtmlCompat(html)
        }
    }
}
