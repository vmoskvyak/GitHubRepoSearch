package com.vmoskvyak.githubreposearch.network.model

class GitHubRepoDetailsData(val totalCount: Int?, val watchers: List<Watcher>) {

    class Watcher(val name: String?, val cursor: String?)

}
