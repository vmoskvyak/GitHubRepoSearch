package com.vmoskvyak.githubreposearch.network.model

class GitHubRepoDetailsData(val totalCount: Int?, val watchers: List<Watcher>) {

    class Watcher(val name: String?, val cursor: String?) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Watcher

            if (name != other.name) return false
            if (cursor != other.cursor) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name?.hashCode() ?: 0
            result = 31 * result + (cursor?.hashCode() ?: 0)
            return result
        }
    }

}
