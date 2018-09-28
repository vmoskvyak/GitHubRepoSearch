package com.vmoskvyak.githubreposearch.ui.fragments.details

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.android.databinding.library.baseAdapters.BR
import com.vmoskvyak.githubreposearch.ui.fragments.data.GitRepoInfoData

class DetailsGitRepoFragmentViewModel(private val gitRepoInfoData: GitRepoInfoData) :
        BaseObservable() {

    @Bindable
    var countOfSubscribers: ObservableField<Int> = ObservableField()

    fun getAvatarUrl(): String {
        return gitRepoInfoData.avatarUrl
    }

    fun getOwnerName(): String {
        return gitRepoInfoData.owner
    }

    fun setCountOfSubscribers(subscribers: Int) {
        countOfSubscribers.set(subscribers)
        notifyPropertyChanged(BR.countOfSubscribers)
    }

    fun getRepositoryName(): String {
        return gitRepoInfoData.name
    }

}
