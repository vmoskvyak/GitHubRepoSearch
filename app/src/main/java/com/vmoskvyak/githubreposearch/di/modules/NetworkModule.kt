package com.vmoskvyak.githubreposearch.di.modules

import com.apollographql.apollo.ApolloClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    private val BASE_URL = "https://api.github.com/graphql"
    private val GITHUB_AUTH_TOKEN = "fa0ec7104d54e21298b05ff349d20ef061076e6b"

    @Provides
    fun provideGson() : Gson {
        return Gson()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val original = it.request()
                    val builder = original.newBuilder().method(original.method(), original.body())
                    builder.header("Authorization", "Bearer $GITHUB_AUTH_TOKEN")
                    it.proceed(builder.build())
                }
                .build()
    }

    @Provides
    fun provideApoloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build()
    }

}
