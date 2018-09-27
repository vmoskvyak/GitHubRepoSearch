package com.vmoskvyak.githubreposearch.ui

import android.os.Bundle
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSearchGitRepoFragment()
    }

    private fun showSearchGitRepoFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, SearchGitRepoFragment(), SearchGitRepoFragment.TAG)
                .commit()
    }

}
