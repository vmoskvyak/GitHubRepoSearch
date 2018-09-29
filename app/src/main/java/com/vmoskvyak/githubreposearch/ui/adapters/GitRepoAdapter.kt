package com.vmoskvyak.githubreposearch.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.githubreposearch.databinding.GitRepoItemBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoItemViewModel

class GitRepoAdapter : PagedListAdapter<GitHubRepoData.RepositoryInfo,
                GitRepoAdapter.DataViewHolder>(DIFF_CALLBACK) {

    lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = GitRepoItemBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding.root)
    }

    override fun onBindViewHolder(holderData: DataViewHolder, position: Int) {
        val repositoryInfo = getItem(position)
        holderData.binding?.viewModel = repositoryInfo?.let { SearchGitRepoItemViewModel(it) }

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

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<GitHubRepoData.RepositoryInfo> =
                object : DiffUtil.ItemCallback<GitHubRepoData.RepositoryInfo>() {
            override fun areItemsTheSame(oldItem: GitHubRepoData.RepositoryInfo,
                                         newItem: GitHubRepoData.RepositoryInfo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GitHubRepoData.RepositoryInfo,
                                            newItem: GitHubRepoData.RepositoryInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

}
