package com.vmoskvyak.githubreposearch.network.model


class GitHubRepoData(val repositoryCount: Int?, val repositoryInfo: List<RepositoryInfo>) {

    class RepositoryInfo(
            val owner: String?,
            val name: String?,
            val avatarUrl: String?,
            val description: String?,
            val numberOfForks: Int?,
            val cursor: String?)

}