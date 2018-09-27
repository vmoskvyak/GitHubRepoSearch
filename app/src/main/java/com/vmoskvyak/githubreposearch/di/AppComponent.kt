package com.vmoskvyak.githubreposearch.di

import android.app.Application
import com.vmoskvyak.githubreposearch.GitHubRepoSearchApplication
import com.vmoskvyak.githubreposearch.di.builder.ActivityBuilder
import com.vmoskvyak.githubreposearch.di.builder.FragmentBuilder
import com.vmoskvyak.githubreposearch.di.modules.DataModule
import com.vmoskvyak.githubreposearch.di.modules.NetworkModule
import com.vmoskvyak.githubreposearch.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    DataModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class])
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
