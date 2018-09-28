package com.vmoskvyak.githubreposearch.ui.fragments.data

import android.os.Parcel
import android.os.Parcelable

class GitRepoInfoData(var owner: String, var name: String, var avatarUrl: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(owner)
        writeString(name)
        writeString(avatarUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GitRepoInfoData> = object : Parcelable.Creator<GitRepoInfoData> {
            override fun createFromParcel(source: Parcel): GitRepoInfoData = GitRepoInfoData(source)
            override fun newArray(size: Int): Array<GitRepoInfoData?> = arrayOfNulls(size)
        }
    }

}
