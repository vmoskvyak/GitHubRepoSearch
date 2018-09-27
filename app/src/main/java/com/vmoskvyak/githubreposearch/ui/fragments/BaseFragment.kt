package com.vmoskvyak.githubreposearch.ui.fragments

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import dagger.android.DaggerFragment

abstract class BaseFragment : DaggerFragment(), LifecycleOwner {
    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }


    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

}
