package com.vmoskvyak.githubreposearch.ui

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.vmoskvyak.githubreposearch.ui.adapters.GitRepoAdapter


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val context = imageView.context
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)
}

@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, adapter: GitRepoAdapter) {
    recyclerView.adapter = adapter
}
