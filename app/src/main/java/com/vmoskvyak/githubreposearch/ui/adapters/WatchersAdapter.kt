package com.vmoskvyak.githubreposearch.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.githubreposearch.databinding.WatcherItemBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.ui.fragments.details.WatcherItemViewModel

class WatchersAdapter : PagedListAdapter<GitHubRepoDetailsData.Watcher,
        WatchersAdapter.WatchersViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchersViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = WatcherItemBinding.inflate(inflater, parent, false)
        return WatchersAdapter.WatchersViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WatchersViewHolder, position: Int) {
        val watcher = getItem(position)
        holder.binding?.viewModel = watcher?.let { WatcherItemViewModel(it) }
    }

    class WatchersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: WatcherItemBinding? = DataBindingUtil.bind(view)

    }

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<GitHubRepoDetailsData.Watcher> =
                object : DiffUtil.ItemCallback<GitHubRepoDetailsData.Watcher>() {
                    override fun areItemsTheSame(oldItem: GitHubRepoDetailsData.Watcher,
                                                 newItem: GitHubRepoDetailsData.Watcher): Boolean {
                        return oldItem == newItem
                    }

                    override fun areContentsTheSame(oldItem: GitHubRepoDetailsData.Watcher,
                                                    newItem: GitHubRepoDetailsData.Watcher): Boolean {
                        return oldItem == newItem
                    }
                }
    }

}
