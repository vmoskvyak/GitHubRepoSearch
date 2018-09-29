package com.vmoskvyak.githubreposearch.di.modules

import com.apollographql.apollo.ApolloClient
import com.vmoskvyak.githubreposearch.BuildConfig
import com.vmoskvyak.githubreposearch.di.scopes.AppScope
import com.vmoskvyak.githubreposearch.network.api.RepoSearchApi
import com.vmoskvyak.githubreposearch.network.api.RepoSearchApiImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideRepoSearchApi(repoSearchApi: RepoSearchApiImpl): RepoSearchApi {
        return repoSearchApi
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val original = it.request()
                    val builder = original.newBuilder().method(original.method(), original.body())
                    builder.header("Authorization", "Bearer ${BuildConfig.GIT_HUB_TOKEN}")
                    it.proceed(builder.build())
                }
                .build()
    }

    @AppScope
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(BuildConfig.BASE_URL)
                .okHttpClient(okHttpClient)
                .build()
    }

}
