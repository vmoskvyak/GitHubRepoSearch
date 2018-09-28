package com.vmoskvyak.githubreposearch.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.githubreposearch.databinding.WatcherItemBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.ui.fragments.details.WatcherItemViewModel

class WatchersAdapter : RecyclerView.Adapter<WatchersAdapter.WatchersViewHolder>() {

    private val watchersList: MutableList<GitHubRepoDetailsData.Watcher> = ArrayList()

    fun setData(dataList: List<GitHubRepoDetailsData.Watcher>) {
        watchersList.clear()
        watchersList.addAll(dataList)

        notifyDataSetChanged()
    }

    fun addItems(dataList: List<GitHubRepoDetailsData.Watcher>) {
        watchersList.addAll(dataList)

        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): GitHubRepoDetailsData.Watcher {
        return watchersList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchersViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = WatcherItemBinding.inflate(inflater, parent, false)
        return WatchersAdapter.WatchersViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return watchersList.size
    }

    override fun onBindViewHolder(holder: WatchersViewHolder, position: Int) {
        val watcher = watchersList[position]
        holder.binding?.viewModel = WatcherItemViewModel(watcher)
    }

    class WatchersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: WatcherItemBinding? = DataBindingUtil.bind(view)

    }
}