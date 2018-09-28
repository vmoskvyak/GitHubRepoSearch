package com.vmoskvyak.githubreposearch.network

import com.apollographql.apollo.ApolloCall
import com.vmoskvyak.githubreposearch.LoadGithubRepositories
import com.vmoskvyak.githubreposearch.LoadGithubRepositoryDetails

interface RepoSearchApi {

    fun getRepositoryByName(name: String, after: String? = null):
            ApolloCall<LoadGithubRepositories.Data>

    fun getRepositoryDetails(owner: String, name: String, after: String? = null):
            ApolloCall<LoadGithubRepositoryDetails.Data>

}
