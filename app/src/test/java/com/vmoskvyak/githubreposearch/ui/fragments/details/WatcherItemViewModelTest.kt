package com.vmoskvyak.githubreposearch.ui.fragments.details

import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import org.junit.Assert.assertEquals
import org.junit.Test

class WatcherItemViewModelTest {

    @Test
    fun getName() {
        val watcherItemViewModel =
                WatcherItemViewModel(GitHubRepoDetailsData.Watcher("name", "cursor"))

        assertEquals("name", watcherItemViewModel.getName())
    }

    @Test
    fun getEmptyName() {
        val watcherItemViewModel =
                WatcherItemViewModel(GitHubRepoDetailsData.Watcher(null, "cursor"))

        assertEquals("no name", watcherItemViewModel.getName())
    }

}
