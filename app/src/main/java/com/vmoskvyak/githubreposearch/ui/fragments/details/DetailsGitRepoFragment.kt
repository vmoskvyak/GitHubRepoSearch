package com.vmoskvyak.githubreposearch.ui.fragments.details

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.databinding.FragmentDetailsGitRepoBinding
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
import com.vmoskvyak.githubreposearch.repository.wrapper.Resource
import com.vmoskvyak.githubreposearch.repository.wrapper.Status
import com.vmoskvyak.githubreposearch.ui.MainActivity
import com.vmoskvyak.githubreposearch.ui.adapters.WatchersAdapter
import com.vmoskvyak.githubreposearch.ui.fragments.BaseFragment
import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData
import com.vmoskvyak.githubreposearch.viewmodel.GitHubRepoDetailsViewModel
import javax.inject.Inject

class DetailsGitRepoFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: GitHubRepoDetailsViewModel

    private lateinit var watchersAdapter: WatchersAdapter
    private lateinit var detailsGitRepoFragmentViewModel: DetailsGitRepoFragmentViewModel

    private lateinit var repoInfoData: GitRepoInfoData

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentDetailsGitRepoBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details_git_repo, container, false)

        if (arguments == null) return binding.root

        repoInfoData = arguments.getParcelable(DETAILS_GIT_REPO_ARGS)
        detailsGitRepoFragmentViewModel = DetailsGitRepoFragmentViewModel(repoInfoData)
        binding.viewModel = detailsGitRepoFragmentViewModel

        watchersAdapter = WatchersAdapter()
        binding.rvWatchers.adapter = watchersAdapter

        initView(binding, repoInfoData)
        (activity as MainActivity)
                .setToolbarTitle(detailsGitRepoFragmentViewModel.getRepositoryName())

        return binding.root
    }

    private fun initView(binding: FragmentDetailsGitRepoBinding, repoInfoData: GitRepoInfoData) {
        val recyclerView = binding.rvWatchers
        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                requestPagination(layoutManager)
            }
        })

        viewModel.getGitHubRepositoryDetails(repoInfoData.owner, repoInfoData.name)
                .observe(this, Observer<Resource<GitHubRepoDetailsData>> {
                    if (it?.status == Status.ERROR) {
                        (activity as MainActivity).showErrorDialog(it.message)
                        return@Observer
                    }

                    detailsGitRepoFragmentViewModel.setCountOfSubscribers(it?.data?.totalCount ?: 0)
                    it?.data?.watchers?.let {
                        list -> watchersAdapter.setData(list)
                    }
                })
    }

    private fun requestPagination(layoutManager: LinearLayoutManager) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
            if (totalItemCount == detailsGitRepoFragmentViewModel.countOfSubscribers.get()) return

            val item = watchersAdapter.getItemByPosition(
                    totalItemCount - 1)

            viewModel.getGitHubRepositoryDetails(repoInfoData.owner, repoInfoData.name, item.cursor)
                    .observe(activity as LifecycleOwner, Observer<Resource<GitHubRepoDetailsData>> {
                        if (it?.status == Status.ERROR) {
                            (activity as MainActivity).showErrorDialog(it.message)
                            return@Observer
                        }
                        it?.data?.watchers?.let {
                            list -> watchersAdapter.addItems(list)
                        }
                    })
        }
    }

    companion object {
        const val TAG: String = "DetailsGitRepoFragment"
        const val DETAILS_GIT_REPO_ARGS = "details_git_repo_args"

        fun newInstance(gitRepoInfoData: GitRepoInfoData): DetailsGitRepoFragment {
            val detailsGitRepoFragment = DetailsGitRepoFragment()
            val args = Bundle()

            args.putParcelable(DETAILS_GIT_REPO_ARGS, gitRepoInfoData)
            detailsGitRepoFragment.arguments = args

            return detailsGitRepoFragment
        }
    }

}
