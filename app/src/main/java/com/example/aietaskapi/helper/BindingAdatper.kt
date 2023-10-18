package com.example.aietaskapi.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.aietaskapi.R

@BindingAdapter("setImages")
fun setImages(imageView: ImageView, resourceId: String) {
    if (resourceId.isNotEmpty()){
        Glide.with(imageView.context)
            .load(resourceId) // URL or resource ID
        .placeholder(R.drawable.ic_loading) // Optional placeholder image
        .error(android.R.drawable.stat_notify_error) // Optional error image
            .into(imageView)
    }

}