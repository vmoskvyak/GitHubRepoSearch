package com.vmoskvyak.githubreposearch.repository

import android.arch.lifecycle.LiveData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData

interface GitHubRepo {
    fun searchRepositoriesByName(name: String, after: String? = null): LiveData<GitHubRepoData>
}