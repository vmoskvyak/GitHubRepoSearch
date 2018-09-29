package com.vmoskvyak.githubreposearch.network.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GitHubRepoDataTest {

    private lateinit var gitHubRepoData: GitHubRepoData
    private var repositoryInfo: ArrayList<GitHubRepoData.RepositoryInfo> = ArrayList()

    @Before
    fun setup() {
        repositoryInfo.add(GitHubRepoData.RepositoryInfo("owner", "name",
                "avatarUrl", "description", 50, "cursor"))
        repositoryInfo.add(GitHubRepoData.RepositoryInfo("owner1", "name1",
                "avatarUrl1", "description1", 10, "cursor1"))
        repositoryInfo.add(GitHubRepoData.RepositoryInfo("owner2", "name2",
                "avatarUrl2", "description2", 20, "cursor2"))

        gitHubRepoData = GitHubRepoData(10, repositoryInfo)
    }

    @Test
    fun getRepositoryCount() {
        val expected: ArrayList<GitHubRepoData.RepositoryInfo> = ArrayList()
        expected.add(GitHubRepoData.RepositoryInfo("owner", "name",
                "avatarUrl", "description", 50, "cursor"))
        expected.add(GitHubRepoData.RepositoryInfo("owner1", "name1",
                "avatarUrl1", "description1", 10, "cursor1"))
        expected.add(GitHubRepoData.RepositoryInfo("owner2", "name2",
                "avatarUrl2", "description2", 20, "cursor2"))
        assertEquals(expected, gitHubRepoData.repositoryInfo)
    }

    @Test
    fun getRepositoryInfo() {
        assertEquals(10, gitHubRepoData.repositoryCount)
    }

}
