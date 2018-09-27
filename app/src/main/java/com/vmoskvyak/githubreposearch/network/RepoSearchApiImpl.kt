package com.vmoskvyak.githubreposearch.network

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.vmoskvyak.githubreposearch.LoadGithubRepositories
import javax.inject.Inject

class RepoSearchApiImpl
@Inject constructor(var apolloClient: ApolloClient): RepoSearchApi {

    override fun getRepositoryByName(name: String, after: String?): ApolloCall<LoadGithubRepositories.Data> {
        val githubRepositories = LoadGithubRepositories.builder()
                .queryString("$name in:name")
                .count(10)
                .after(after)
                .build()
        return apolloClient.query(githubRepositories)
    }

}
