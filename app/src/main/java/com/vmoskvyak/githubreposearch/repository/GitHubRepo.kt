package com.vmoskvyak.githubreposearch.repository

import android.arch.lifecycle.LiveData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData

interface GitHubRepo {

    fun searchRepositoriesByName(name: String, after: String? = null): LiveData<GitHubRepoData>

    fun getRepositoryDetails(
            owner: String, name: String, after: String? = null): LiveData<GitHubRepoDetailsData>

}
