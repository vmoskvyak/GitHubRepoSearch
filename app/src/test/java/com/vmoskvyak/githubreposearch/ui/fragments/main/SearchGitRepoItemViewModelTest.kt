package com.vmoskvyak.githubreposearch.ui.fragments.main

import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchGitRepoItemViewModelTest {

    private lateinit var searchGitRepoItemViewModel: SearchGitRepoItemViewModel

    @Before
    fun setup() {
        val repositoryInfo = GitHubRepoData.RepositoryInfo("owner", "name",
                "avatarUrl", "description", 10, "cursor")
        searchGitRepoItemViewModel = SearchGitRepoItemViewModel(repositoryInfo)
    }

    @Test
    fun getName() {
        assertEquals("name", searchGitRepoItemViewModel.getName())
    }

    @Test
    fun getAvatarUrl() {
        assertEquals("avatarUrl", searchGitRepoItemViewModel.getAvatarUrl())
    }

    @Test
    fun getNumberOfForks() {
        assertEquals("10", searchGitRepoItemViewModel.getNumberOfForks())
    }

    @Test
    fun getDescription() {
        assertEquals("description", searchGitRepoItemViewModel.getDescription())
    }

    @Test
    fun getOwner() {
        assertEquals("owner", searchGitRepoItemViewModel.getOwner())
    }

}
