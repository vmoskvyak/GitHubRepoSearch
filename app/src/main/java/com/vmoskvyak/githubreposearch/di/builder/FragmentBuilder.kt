package com.vmoskvyak.githubreposearch.di.builder

import com.vmoskvyak.githubreposearch.ui.fragments.details.DetailsGitRepoFragment
import com.vmoskvyak.githubreposearch.ui.fragments.main.SearchGitRepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeSearchGitRepoFragment(): SearchGitRepoFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsGitRepoFragment(): DetailsGitRepoFragment

}
