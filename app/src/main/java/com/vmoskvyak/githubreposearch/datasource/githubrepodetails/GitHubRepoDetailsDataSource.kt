package com.vmoskvyak.githubreposearch.datasource.githubrepodetails

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import kotlinx.coroutines.experimental.launch

class GitHubRepoDetailsDataSource(
        private var repository: GitHubRepo,
        private var owner: String,
        private var name: String,
        private var countOfSubscribers: MutableLiveData<Int>,
        private val requestStatus: MutableLiveData<RequestStatus>) :
        PageKeyedDataSource<String, GitHubRepoDetailsData.Watcher>() {

    private var sourceIndex: Int = 0

    override fun loadInitial(params: LoadInitialParams<String>,
                             callback: LoadInitialCallback<String, GitHubRepoDetailsData.Watcher>) {
        launch {
            try {
                val repositoryDetails = repository.getRepositoryDetails(owner, name)
                countOfSubscribers.postValue(repositoryDetails.totalCount)

                val watchers = repositoryDetails.watchers
                sourceIndex += watchers.count()
                callback.onResult(watchers, null, watchers.last().cursor)

            } catch (throwable: Throwable) {
                requestStatus.postValue(
                        RequestStatus(RequestStatus.Status.ERROR, throwable.message))
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>,
                           callback: LoadCallback<String, GitHubRepoDetailsData.Watcher>) {
        launch {
            val repositoryDetails = repository.getRepositoryDetails(owner, name, params.key)

            val totalCount = repositoryDetails.totalCount
            val watchers = repositoryDetails.watchers

            val cursor = when {
                totalCount == null -> null
                totalCount <= sourceIndex -> null
                else -> {
                    sourceIndex += watchers.count()
                    watchers.last().cursor
                }
            }

            callback.onResult(watchers, cursor)
        }
    }

    override fun loadBefore(params: LoadParams<String>,
                            callback: LoadCallback<String, GitHubRepoDetailsData.Watcher>) {
    }

}
