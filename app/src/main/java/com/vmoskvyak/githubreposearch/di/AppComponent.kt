package com.vmoskvyak.githubreposearch.di

import android.app.Application
import com.vmoskvyak.githubreposearch.GitHubRepoSearchApplication
import com.vmoskvyak.githubreposearch.di.builder.ActivityBuilder
import com.vmoskvyak.githubreposearch.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ActivityBuilder::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: GitHubRepoSearchApplication)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }


}
