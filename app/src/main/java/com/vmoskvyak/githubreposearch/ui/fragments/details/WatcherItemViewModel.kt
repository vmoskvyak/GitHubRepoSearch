package com.vmoskvyak.githubreposearch.ui.fragments.details

import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData

class WatcherItemViewModel(private val watcher: GitHubRepoDetailsData.Watcher) {

    fun getName(): String {
        return watcher.name ?: "no name"
    }

}
