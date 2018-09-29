package com.vmoskvyak.githubreposearch.ui.fragments.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GitRepoInfoDataTest {

    private lateinit var gitRepoInfoData : GitRepoInfoData

    @Before
    fun setup() {
        gitRepoInfoData = GitRepoInfoData("owner", "name", "avatarUrl")
    }

    @Test
    fun getOwner() {
        assertEquals("owner", gitRepoInfoData.owner)
    }

    @Test
    fun setOwner() {
        gitRepoInfoData.owner = "newOwner"
        assertEquals("newOwner", gitRepoInfoData.owner)
    }

    @Test
    fun getName() {
        assertEquals("name", gitRepoInfoData.name)
    }

    @Test
    fun setName() {
        gitRepoInfoData.name = "newName"
        assertEquals("newName", gitRepoInfoData.name)
    }

    @Test
    fun getAvatarUrl() {
        assertEquals("avatarUrl", gitRepoInfoData.avatarUrl)
    }

    @Test
    fun setAvatarUrl() {
        gitRepoInfoData.avatarUrl = "newUrl"
        assertEquals("newUrl", gitRepoInfoData.avatarUrl)
    }

}
