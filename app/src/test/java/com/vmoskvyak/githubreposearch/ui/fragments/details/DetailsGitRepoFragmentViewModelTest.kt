package com.vmoskvyak.githubreposearch.ui.fragments.details

import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailsGitRepoFragmentViewModelTest {

    private lateinit var detailsGitRepoFragmentViewModel: DetailsGitRepoFragmentViewModel

    @Before
    fun setup() {
        val gitRepoInfoData = GitRepoInfoData("owner", "name", "avatarUrl")
        detailsGitRepoFragmentViewModel = DetailsGitRepoFragmentViewModel(gitRepoInfoData)
        detailsGitRepoFragmentViewModel.countOfSubscribers.set(10)
    }

    @Test
    fun getCountOfSubscribers() {
        assertEquals(10, detailsGitRepoFragmentViewModel.countOfSubscribers.get())
    }

    @Test
    fun setCountOfSubscribers() {
        detailsGitRepoFragmentViewModel.countOfSubscribers.set(15)
        assertEquals(15, detailsGitRepoFragmentViewModel.countOfSubscribers.get())
    }

    @Test
    fun getAvatarUrl() {
        assertEquals("avatarUrl", detailsGitRepoFragmentViewModel.getAvatarUrl())
    }

    @Test
    fun getOwnerName() {
        assertEquals("owner", detailsGitRepoFragmentViewModel.getOwnerName())
    }

    @Test
    fun getRepositoryName() {
        assertEquals("name", detailsGitRepoFragmentViewModel.getRepositoryName())
    }

}
