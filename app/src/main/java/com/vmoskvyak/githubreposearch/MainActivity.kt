package com.vmoskvyak.githubreposearch

import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.vmoskvyak.githubreposearch.network.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.RepoSearchApiImpl
import com.vmoskvyak.githubreposearch.repository.GitHubRepoImpl
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api : RepoSearchApi = RepoSearchApiImpl(apolloClient)

        val repo = GitHubRepoImpl(api)

        launch {
            val repositoryByName = repo.getRepositoryByName("socio")
            Log.d("test", repositoryByName.repositoryInfo[0].avatarUrl)

        }

    }

}
