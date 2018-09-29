package com.vmoskvyak.githubreposearch.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vmoskvyak.githubreposearch.di.scopes.AppScope
import com.vmoskvyak.githubreposearch.repository.GitHubRepo
import com.vmoskvyak.githubreposearch.repository.GitHubRepoImpl
import com.vmoskvyak.githubreposearch.viewmodel.GitHubRepoDetailsViewModel
import com.vmoskvyak.githubreposearch.viewmodel.GitHubRepoViewModel
import com.vmoskvyak.githubreposearch.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @AppScope
    @Provides
    fun provideGitHubRepository(gitHubRepo: GitHubRepoImpl): GitHubRepo {
        return gitHubRepo
    }

    @AppScope
    @Provides
    fun provideGitHubRepoViewModel(viewModel: GitHubRepoViewModel): ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideGitHubRepoDetailsViewModel(viewModel: GitHubRepoDetailsViewModel): ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory {
        return viewModelFactory
    }

}
