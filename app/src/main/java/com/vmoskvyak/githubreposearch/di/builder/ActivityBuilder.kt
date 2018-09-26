package com.vmoskvyak.githubreposearch.di.builder

import com.vmoskvyak.githubreposearch.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
