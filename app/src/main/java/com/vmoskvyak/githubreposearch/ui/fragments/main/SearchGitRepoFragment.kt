package com.vmoskvyak.githubreposearch.ui.fragments.main

import android.app.SearchManager
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.databinding.FragmentSearchGitRepoBinding
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoData
import com.vmoskvyak.githubreposearch.ui.MainActivity
import com.vmoskvyak.githubreposearch.ui.adapters.GitRepoAdapter
import com.vmoskvyak.githubreposearch.ui.adapters.OnItemClickListener
import com.vmoskvyak.githubreposearch.ui.fragments.BaseFragment
import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData
import com.vmoskvyak.githubreposearch.ui.fragments.details.DetailsGitRepoFragment
import com.vmoskvyak.githubreposearch.viewmodel.GitHubRepoViewModel
import javax.inject.Inject

class SearchGitRepoFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: GitHubRepoViewModel

    private lateinit var searchView: SearchView
    private lateinit var gitRepoAdapter: GitRepoAdapter

    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(SEARCH_QUERY_ARG)
        }

        viewModel.requestStatus.observe(this, Observer<RequestStatus> {
            if (it?.status == RequestStatus.Status.ERROR) {
                (activity as MainActivity).showErrorDialog(it.message)
            }
        } )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentSearchGitRepoBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_git_repo, container, false)

        val searchGitRepoFragmentViewModel = SearchGitRepoFragmentViewModel()
        gitRepoAdapter = searchGitRepoFragmentViewModel.gitRepoAdapter
        initOnGitRepoInfoClick()

        binding.viewModel = searchGitRepoFragmentViewModel

        initRecycleView(binding)

        return binding.root
    }

    private fun initOnGitRepoInfoClick() {
        gitRepoAdapter.onItemClickListener = object : OnItemClickListener {

            override fun onItemClick(gitRepoInfoData: GitRepoInfoData) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_container,
                                DetailsGitRepoFragment.newInstance(gitRepoInfoData),
                                DetailsGitRepoFragment.TAG)
                        .addToBackStack(SearchGitRepoFragment.TAG)
                        .commit()
            }
        }
    }

    private fun initRecycleView(binding: FragmentSearchGitRepoBinding) {
        val recyclerView = binding.rvGitRepoList

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.isNestedScrollingEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))

        searchView.isIconified = true
        searchView.onActionViewExpanded()

        if (!searchQuery.isEmpty()) {
            searchView.setQuery(searchQuery, true)
            sendSearchRequest(searchQuery)
        }

        searchView.isFocusable = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) return true

                searchQuery = newText
                sendSearchRequest(newText)

                return true
            }
        })
    }

    private fun sendSearchRequest(newText: String) {
        viewModel.searchGitHubRepository(newText)
                .observe(activity as LifecycleOwner,
                        Observer<PagedList<GitHubRepoData.RepositoryInfo>> {
                    gitRepoAdapter.submitList(it)
                })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SEARCH_QUERY_ARG, searchQuery)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val TAG: String = "SearchGitRepoFragment"
        const val SEARCH_QUERY_ARG: String = "SearchGitRepoFragment.SearchQuery"
    }

}
