package com.vmoskvyak.githubreposearch.ui.fragments.main

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.databinding.FragmentSearchGitRepoBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.ui.adapters.GitRepoAdapter
import com.vmoskvyak.githubreposearch.ui.fragments.BaseFragment
import com.vmoskvyak.githubreposearch.viewmodel.GitHubRepoViewModel
import javax.inject.Inject

class SearchGitRepoFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: GitHubRepoViewModel

    private lateinit var searchView: SearchView
    private lateinit var gitRepoAdapter: GitRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding : FragmentSearchGitRepoBinding = DataBindingUtil.inflate(
                inflater ,R.layout.fragment_search_git_repo,container , false)

        val searchGitRepoFragmentViewModel = SearchGitRepoFragmentViewModel()
        gitRepoAdapter = searchGitRepoFragmentViewModel.gitRepoAdapter

        binding.viewModel = searchGitRepoFragmentViewModel

        initRecycleView(binding)

        return binding.root
    }

    private fun initRecycleView(binding: FragmentSearchGitRepoBinding) {
        val recyclerView = binding.rvGitRepoList

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.isNestedScrollingEnabled = false

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                requestPagination(layoutManager)
            }
        })
    }

    private fun requestPagination(layoutManager: LinearLayoutManager) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + pastVisibleItems >= totalItemCount) {

            if (searchView.query.toString().isEmpty()) return
            if (totalItemCount == gitRepoAdapter.gitReposCount) return

            val itemByPosition = gitRepoAdapter.getItemByPosition(
                    totalItemCount - 1)

            viewModel.searchGitHubRepository(
                    searchView.query.toString(), itemByPosition.cursor)
                    .observe(activity as LifecycleOwner, Observer<GitHubRepoData> {
                        it?.repositoryInfo?.let { it1 -> gitRepoAdapter.addItems(it1) }
                    })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchGitHubRepository(newText)
                        .observe(activity as LifecycleOwner, Observer<GitHubRepoData> {
                            it?.let { gitHubRepoData -> gitRepoAdapter.setData(gitHubRepoData) }
                        })

                return true
            }
        })

    }

    companion object {
        const val TAG: String = "SearchGitRepoFragment"
    }

}
