package com.vmoskvyak.githubreposearch.network.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GitHubRepoDetailsDataTest {

    private lateinit var gitHubRepoDetailsData: GitHubRepoDetailsData
    private var watchers: ArrayList<GitHubRepoDetailsData.Watcher> = ArrayList()

    @Before
    fun setup() {
        watchers.add(GitHubRepoDetailsData.Watcher("name", "cursor"))
        watchers.add(GitHubRepoDetailsData.Watcher("name1", "cursor1"))
        watchers.add(GitHubRepoDetailsData.Watcher("name2", "cursor2"))

        gitHubRepoDetailsData = GitHubRepoDetailsData(10, watchers)
    }

    @Test
    fun getTotalCount() {
        assertEquals(10, gitHubRepoDetailsData.totalCount)
    }

    @Test
    fun getWatchers() {
        val expected: ArrayList<GitHubRepoDetailsData.Watcher> = ArrayList()

        expected.add(GitHubRepoDetailsData.Watcher("name", "cursor"))
        expected.add(GitHubRepoDetailsData.Watcher("name1", "cursor1"))
        expected.add(GitHubRepoDetailsData.Watcher("name2", "cursor2"))

        assertEquals(expected, gitHubRepoDetailsData.watchers)
    }

}
