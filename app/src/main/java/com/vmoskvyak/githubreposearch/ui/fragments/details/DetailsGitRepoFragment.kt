package com.vmoskvyak.githubreposearch.ui.fragments.details

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.vmoskvyak.githubreposearch.R
import com.vmoskvyak.githubreposearch.databinding.FragmentDetailsGitRepoBinding
import com.vmoskvyak.githubreposearch.network.RequestStatus
import com.vmoskvyak.githubreposearch.network.model.GitHubRepoDetailsData
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestStatus.observe(this, Observer<RequestStatus> {
            if (it?.status == RequestStatus.Status.ERROR) {
                (activity as MainActivity).showErrorDialog(it.message)
            }
        } )

        viewModel.countOfSubscribers.observe(this, Observer<Int> {
            detailsGitRepoFragmentViewModel.countOfSubscribers.set(it ?: 0)
        })
    }

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

        viewModel.getGitHubRepositoryDetails(repoInfoData.owner, repoInfoData.name)
                .observe(this, Observer<PagedList<GitHubRepoDetailsData.Watcher>> {
                    watchersAdapter.submitList(it)
                })
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
