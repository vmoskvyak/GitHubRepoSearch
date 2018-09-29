package com.vmoskvyak.githubreposearch.datasource.githubrepodetails

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo

class GitHubRepoDetailsDataSourceFactory(
        private val gitHubRepo: GitHubRepo,
        private val owner: String,
        private val name: String,
        private var countOfSubscribers: MutableLiveData<Int>,
        private val requestStatus: MutableLiveData<RequestStatus>) :
        DataSource.Factory<String, GitHubRepoDetailsData.Watcher>() {

    private val gitHubRepoDetailsData: MutableLiveData<GitHubRepoDetailsDataSource> = MutableLiveData()

    override fun create(): DataSource<String, GitHubRepoDetailsData.Watcher> {
        val gitHubRepoDetailsDataSource = GitHubRepoDetailsDataSource(
                gitHubRepo, owner, name, countOfSubscribers, requestStatus)
        gitHubRepoDetailsData.postValue(gitHubRepoDetailsDataSource)

        return gitHubRepoDetailsDataSource
    }

}
