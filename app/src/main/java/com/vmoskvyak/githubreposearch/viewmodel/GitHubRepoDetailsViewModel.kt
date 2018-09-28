package com.vmoskvyak.githubreposearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import javax.inject.Inject

class GitHubRepoDetailsViewModel
@Inject constructor(private var gitHubRepo: GitHubRepo): ViewModel() {

    fun getGitHubRepositoryDetails(
            owner: String, name: String, after: String? = null) : LiveData<GitHubRepoDetailsData> {
        return gitHubRepo.getRepositoryDetails(owner, name, after)
    }

}
