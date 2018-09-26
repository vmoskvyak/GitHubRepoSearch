package com.vmoskvyak.githubreposearch.network

import com.apollographql.apollo.ApolloCall
import com.vmoskvyak.githubreposearch.LoadGithubRepositories

interface RepoSearchApi {

    fun getRepositoryByName(name: String): ApolloCall<LoadGithubRepositories.Data>

}
