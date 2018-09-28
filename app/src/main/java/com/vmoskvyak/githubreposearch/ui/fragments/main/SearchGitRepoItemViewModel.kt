package com.vmoskvyak.githubreposearch.ui.fragments.main

import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData

class SearchGitRepoItemViewModel(private val repositoryInfo: GitHubRepoData.RepositoryInfo) {

    fun getName() : String {
        return repositoryInfo.name ?: ""
    }

    fun getAvatarUrl(): String {
        return repositoryInfo.avatarUrl ?: ""
    }

    fun getNumberOfForks(): String? {
        return repositoryInfo.numberOfForks.toString()
    }

    fun getDescription(): String? {
        return repositoryInfo.description
    }

    fun getOwner(): String {
        return repositoryInfo.owner ?: ""
    }

}
