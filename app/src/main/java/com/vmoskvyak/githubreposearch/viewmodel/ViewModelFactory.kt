package com.vmoskvyak.githubreposearch.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject
constructor(private val mViewModel: GitHubRepoViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubRepoViewModel::class.java)) {
            return mViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
