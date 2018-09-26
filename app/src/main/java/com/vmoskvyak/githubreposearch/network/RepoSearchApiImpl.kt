package com.vmoskvyak.githubreposearch.network

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.vmoskvyak.githubreposearch.LoadGithubRepositories

class RepoSearchApiImpl(var apolloClient: ApolloClient): RepoSearchApi {

    override fun getRepositoryByName(name: String): ApolloCall<LoadGithubRepositories.Data> {
        val githubRepositories = LoadGithubRepositories.builder()
                .queryString("$name in:name")
                .count(10)
                .build()
        return apolloClient.query(githubRepositories)
    }

}
