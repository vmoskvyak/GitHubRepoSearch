package com.vmoskvyak.githubreposearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import com.vmoskvyak.githubreposearch.repository.wrapper.Resource
import javax.inject.Inject

class GitHubRepoViewModel @Inject constructor(private var gitHubRepo: GitHubRepo) : ViewModel() {

    fun searchGitHubRepository(name: String, after: String? = null):
            LiveData<Resource<GitHubRepoData>> {
        return gitHubRepo.searchRepositoriesByName(name, after)
    }

}
