package com.vmoskvyak.githubreposearch.network.model


class RepositoryModel(val repositoryCount: Int?, val repositoryInfo: List<RepositoryInfo>) {

    class RepositoryInfo(val nnme: String?,
                         val avatarUrl: String?,
                         val desctiption: String?,
                         val numberOfForks: Int?)

}