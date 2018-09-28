package com.vmoskvyak.githubreposearch.repository

import android.arch.lifecycle.LiveData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.wrapper.Resource

interface GitHubRepo {

    fun searchRepositoriesByName(name: String, after: String? = null):
            LiveData<Resource<GitHubRepoData>>

    fun getRepositoryDetails(
            owner: String, name: String, after: String? = null):
            LiveData<Resource<GitHubRepoDetailsData>>

}
