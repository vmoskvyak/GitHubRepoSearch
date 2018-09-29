package com.vmoskvyak.githubreposearch.repository

import com.vmoskvyak.githubreposearch.await
import com.vmoskvyak.githubreposearch.network.api.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import java.util.*
import javax.inject.Inject

class GitHubRepoImpl
@Inject constructor(private val api: RepoSearchApi) : GitHubRepo {

    override suspend fun searchRepositoriesByName(name: String, after: String?): GitHubRepoData {
        val response = api.getRepositoryByName(name, after).await()
        val repositoryInfos = ArrayList<GitHubRepoData.RepositoryInfo>()
        val searchResult = response.data()?.search()

        searchResult?.edges()?.forEach {
            val asRepository = it.node()?.asRepository()
            val owner = asRepository?.owner()

            repositoryInfos.add(GitHubRepoData.RepositoryInfo(
                    owner?.login(), asRepository?.name(),
                    owner?.avatarUrl().toString(), asRepository?.description(),
                    asRepository?.forks()?.totalCount(), it.cursor()))
        }

        return GitHubRepoData(searchResult?.repositoryCount(), repositoryInfos)
    }

    override suspend fun getRepositoryDetails(
            owner: String, name: String, after: String?): GitHubRepoDetailsData {

        val response = api.getRepositoryDetails(owner, name, after).await()
        val watchers = ArrayList<GitHubRepoDetailsData.Watcher>()
        val searchResult = response.data()?.repository()?.watchers()

        searchResult?.edges()?.forEach {
            watchers.add(GitHubRepoDetailsData.Watcher(it?.node()?.name(), it?.cursor()))
        }
        return GitHubRepoDetailsData(searchResult?.totalCount(), watchers)
    }

}
