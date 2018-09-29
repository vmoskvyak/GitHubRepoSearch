package com.vmoskvyak.githubreposearch.datasource.githubrepo

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo

class GitHubRepoDataSourceFactory(
        private val gitHubRepo: GitHubRepo,
        private val name: String,
        private val requestStatus: MutableLiveData<RequestStatus>) :
        DataSource.Factory<String, GitHubRepoData.RepositoryInfo>() {

    private val gitHubRepoData: MutableLiveData<GitHubRepoDataSource> = MutableLiveData()

    override fun create(): DataSource<String, GitHubRepoData.RepositoryInfo> {
        val gitHubRepoDataSource = GitHubRepoDataSource(gitHubRepo, name, requestStatus)
        gitHubRepoData.postValue(gitHubRepoDataSource)

        return gitHubRepoDataSource
    }

}
