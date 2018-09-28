package com.vmoskvyak.githubreposearch.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.githubreposearch.await
import com.vmoskvyak.githubreposearch.network.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.wrapper.Resource
import com.vmoskvyak.githubreposearch.repository.wrapper.Status
import kotlinx.coroutines.experimental.launch
import java.util.*
import javax.inject.Inject

class GitHubRepoImpl
@Inject constructor(private val api: RepoSearchApi) : GitHubRepo {

    override fun searchRepositoriesByName(name: String, after: String?):
            LiveData<Resource<GitHubRepoData>> {

        val result: MutableLiveData<Resource<GitHubRepoData>> = MutableLiveData()

        launch {
            try {
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
                result.postValue(Resource(Status.SUCCESS,
                        GitHubRepoData(searchResult?.repositoryCount(), repositoryInfos)))
            } catch (e: Throwable) {
                result.postValue(Resource(Status.ERROR, message = e.message))
            }
        }

        return result
    }

    override fun getRepositoryDetails(
            owner: String, name: String, after: String?):
            LiveData<Resource<GitHubRepoDetailsData>> {
        val result: MutableLiveData<Resource<GitHubRepoDetailsData>> = MutableLiveData()

        launch {
            try {
                val response = api.getRepositoryDetails(owner, name, after).await()
                val watchers = ArrayList<GitHubRepoDetailsData.Watcher>()
                val searchResult = response.data()?.repository()?.watchers()

                searchResult?.edges()?.forEach {
                    watchers.add(GitHubRepoDetailsData.Watcher(it?.node()?.name(), it?.cursor()))
                }
                result.postValue(Resource(Status.SUCCESS,
                        GitHubRepoDetailsData(searchResult?.totalCount(), watchers)))
            } catch (e: Throwable) {
                result.postValue(Resource(Status.ERROR, message = e.message))
            }
        }

        return result
    }

}
