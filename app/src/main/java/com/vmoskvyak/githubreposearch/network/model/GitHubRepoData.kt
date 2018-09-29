package com.vmoskvyak.githubreposearch.network.model

class GitHubRepoData(val repositoryCount: Int?, val repositoryInfo: List<RepositoryInfo>) {

    class RepositoryInfo(
            val owner: String?,
            val name: String?,
            val avatarUrl: String?,
            val description: String?,
            val numberOfForks: Int?,
            val cursor: String?) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as RepositoryInfo

            if (owner != other.owner) return false
            if (name != other.name) return false
            if (avatarUrl != other.avatarUrl) return false
            if (description != other.description) return false
            if (numberOfForks != other.numberOfForks) return false
            if (cursor != other.cursor) return false

            return true
        }

        override fun hashCode(): Int {
            var result = owner?.hashCode() ?: 0
            result = 31 * result + (name?.hashCode() ?: 0)
            result = 31 * result + (avatarUrl?.hashCode() ?: 0)
            result = 31 * result + (description?.hashCode() ?: 0)
            result = 31 * result + (numberOfForks ?: 0)
            result = 31 * result + (cursor?.hashCode() ?: 0)
            return result
        }
    }

}
