package com.vmoskvyak.githubreposearch.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        showSearchGitRepoFragment()
    }

    fun setToolbarTitle(title: String) {
        toolbar.title = title
    }

    private fun showSearchGitRepoFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, SearchGitRepoFragment(), SearchGitRepoFragment.TAG)
                .commit()
    }

}
