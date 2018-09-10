package com.dkrasnov.jobs.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dkrasnov.jobs.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_splash)

        startActivity(SearchActivity.createIntent(this))
        finish()
    }
}
