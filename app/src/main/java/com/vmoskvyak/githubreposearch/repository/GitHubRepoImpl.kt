package com.vmoskvyak.githubreposearch.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.githubreposearch.await
import com.vmoskvyak.githubreposearch.network.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import kotlinx.coroutines.experimental.launch
import java.util.*
import javax.inject.Inject

class GitHubRepoImpl
@Inject constructor(private val api: RepoSearchApi) : GitHubRepo {

    override fun searchRepositoriesByName(name: String, after: String?): LiveData<GitHubRepoData> {

        val result: MutableLiveData<GitHubRepoData> = MutableLiveData()

        launch {
            val response = api.getRepositoryByName(name, after).await()
            val repositoryInfos = ArrayList<GitHubRepoData.RepositoryInfo>()
            val searchResult = response.data()?.search()

            searchResult?.edges()?.forEach {
                val asRepository = it.node()?.asRepository()
                val owner = asRepository?.owner()

                repositoryInfos.add(GitHubRepoData.RepositoryInfo(asRepository?.name(),
                        owner?.avatarUrl().toString(), asRepository?.description(),
                        asRepository?.forks()?.totalCount(), it.cursor()))
                result.postValue(GitHubRepoData(searchResult.repositoryCount(), repositoryInfos))
            }
        }

        return result
    }

}
