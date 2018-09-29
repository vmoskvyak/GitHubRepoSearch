package com.vmoskvyak.githubreposearch.repository

import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData

interface GitHubRepo {

    suspend fun searchRepositoriesByName(name: String, after: String? = null): GitHubRepoData

    suspend fun getRepositoryDetails(
            owner: String, name: String, after: String? = null): GitHubRepoDetailsData

}
