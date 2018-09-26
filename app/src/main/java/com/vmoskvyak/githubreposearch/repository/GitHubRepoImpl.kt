package com.vmoskvyak.githubreposearch.repository

import com.vmoskvyak.githubreposearch.await
import com.vmoskvyak.githubreposearch.network.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.model.RepositoryModel
import java.util.*

class GitHubRepoImpl(private val api: RepoSearchApi) : GitHubRepo {

    suspend fun getRepositoryByName(query: String) : RepositoryModel{
        val response = api.getRepositoryByName(query).await()

        val repositoryInfos = ArrayList<RepositoryModel.RepositoryInfo>()
        val searchResult = response.data()?.search()

        searchResult?.edges()?.forEach {
            val asRepository = it.node()?.asRepository()
            val owner = asRepository?.owner()

            repositoryInfos.add(RepositoryModel.RepositoryInfo(asRepository?.name(),
                    owner?.avatarUrl().toString(), asRepository?.description(),
                    asRepository?.forks()?.totalCount()))
        }

        return RepositoryModel(searchResult?.repositoryCount(), repositoryInfos)
    }

}
