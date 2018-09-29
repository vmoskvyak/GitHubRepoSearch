package com.vmoskvyak.githubreposearch.datasource.githubrepo

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.RequestStatus.Status
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import kotlinx.coroutines.experimental.launch

class GitHubRepoDataSource(
        private var repository: GitHubRepo,
        private var name: String,
        private val requestStatus: MutableLiveData<RequestStatus>) :
        PageKeyedDataSource<String, GitHubRepoData.RepositoryInfo>() {

    private var sourceIndex: Int = 0

    override fun loadInitial(
            params: LoadInitialParams<String>,
            callback: LoadInitialCallback<String, GitHubRepoData.RepositoryInfo>) {
        launch {
            try {
                val repositoriesByName = repository.searchRepositoriesByName(name)

                val repositoryInfo = repositoriesByName.repositoryInfo
                sourceIndex += repositoryInfo.count()
                callback.onResult(repositoryInfo, null, repositoryInfo.last().cursor)

            } catch (throwable: Throwable) {
                requestStatus.postValue(RequestStatus(Status.ERROR, throwable.message))
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>,
                           callback: LoadCallback<String, GitHubRepoData.RepositoryInfo>) {
        launch {
            try {
                val repositoriesByName = repository.searchRepositoriesByName(name, params.key)

                val repositoryCount = repositoriesByName.repositoryCount
                val repositoryInfo = repositoriesByName.repositoryInfo

                val cursor = when {
                    repositoryCount == null -> null
                    repositoryCount <= sourceIndex -> null
                    else -> {
                        sourceIndex += repositoryInfo.count()
                        repositoryInfo.last().cursor
                    }
                }

                callback.onResult(repositoryInfo, cursor)

            } catch (throwable: Throwable) {
                requestStatus.postValue(RequestStatus(Status.ERROR, throwable.message))
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>,
                            callback: LoadCallback<String, GitHubRepoData.RepositoryInfo>) {

    }

}
