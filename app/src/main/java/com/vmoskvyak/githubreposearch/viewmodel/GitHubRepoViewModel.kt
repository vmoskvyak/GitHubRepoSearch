package com.vmoskvyak.githubreposearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.vmoskvyak.githubreposearch.datasource.githubrepo.GitHubRepoDataSourceFactory
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import javax.inject.Inject

class GitHubRepoViewModel @Inject constructor(private var gitHubRepo: GitHubRepo) : ViewModel() {

    val requestStatus: MutableLiveData<RequestStatus> = MutableLiveData()

    private val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10).build()

    fun searchGitHubRepository(name: String):
            LiveData<PagedList<GitHubRepoData.RepositoryInfo>> {

        val gitHubRepoDataSourceFactory = GitHubRepoDataSourceFactory(
                gitHubRepo, name, requestStatus)

        return LivePagedListBuilder(gitHubRepoDataSourceFactory, pagedListConfig)
                .build()
    }

}
