package com.vmoskvyak.githubreposearch.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.githubreposearch.databinding.GitRepoItemBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoItemViewModel

class GitRepoAdapter : RecyclerView.Adapter<GitRepoAdapter.DataViewHolder>() {

    private val gitReposList: MutableList<GitHubRepoData.RepositoryInfo> = ArrayList()
    var gitReposCount = 0

    lateinit var onItemClickListener: OnItemClickListener

    fun setData(gitHubRepoData: GitHubRepoData) {
        gitReposList.clear()
        gitReposList.addAll(gitHubRepoData.repositoryInfo)
        gitReposCount = gitHubRepoData.repositoryCount ?: 0

        notifyDataSetChanged()
    }

    fun addItems(repoInfoList: List<GitHubRepoData.RepositoryInfo>) {
        gitReposList.addAll(repoInfoList)

        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): GitHubRepoData.RepositoryInfo {
        return gitReposList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = GitRepoItemBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return gitReposList.size
    }

    override fun onBindViewHolder(holderData: DataViewHolder, position: Int) {
        val repositoryInfo = gitReposList[position]
        holderData.binding?.viewModel = SearchGitRepoItemViewModel(repositoryInfo)

        holderData.binding?.click = object : OnGitRepoInfoClickListener {
            override fun onGitRepoInfoClick(gitRepoItemViewModel: SearchGitRepoItemViewModel) {
                onItemClickListener.onItemClick(GitRepoInfoData(
                        gitRepoItemViewModel.getOwner(), gitRepoItemViewModel.getName(),
                        gitRepoItemViewModel.getAvatarUrl()))
            }

        }
    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: GitRepoItemBinding? = DataBindingUtil.bind(view)

    }

    interface OnGitRepoInfoClickListener {

        fun onGitRepoInfoClick(gitRepoItemViewModel: SearchGitRepoItemViewModel)

    }

}
