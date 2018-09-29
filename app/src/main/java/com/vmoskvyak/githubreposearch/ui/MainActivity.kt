package com.vmoskvyak.githubreposearch.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.databinding.ActivityMainBinding
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        toolbar = binding.toolbar
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

    fun showErrorDialog(message: String?) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Error")
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

}
