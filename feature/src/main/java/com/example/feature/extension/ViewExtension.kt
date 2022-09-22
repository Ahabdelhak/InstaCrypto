package com.example.feature.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation

fun ImageView.loadImagesWithGlide(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}


fun ImageView.loadImagesWithCoil(url: String) {
    load(url) {
        crossfade(true)
        transformations(CircleCropTransformation()).diskCachePolicy(CachePolicy.ENABLED)
    }
}