package com.vmoskvyak.githubreposearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vmoskvyak.githubreposearch.datasource.githubrepodetails.GitHubRepoDetailsDataSourceFactory
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import javax.inject.Inject

class GitHubRepoDetailsViewModel
@Inject constructor(private var gitHubRepo: GitHubRepo) : ViewModel() {

    val requestStatus: MutableLiveData<RequestStatus> = MutableLiveData()
    val countOfSubscribers: MutableLiveData<Int> = MutableLiveData()

    private val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10).build()

    fun getGitHubRepositoryDetails(owner: String, name: String):
            LiveData<PagedList<GitHubRepoDetailsData.Watcher>> {

        val gitHubRepoDetailsDataSourceFactory = GitHubRepoDetailsDataSourceFactory(
                gitHubRepo, owner, name, countOfSubscribers, requestStatus)

        return LivePagedListBuilder(gitHubRepoDetailsDataSourceFactory, pagedListConfig)
                .build()
    }

}
