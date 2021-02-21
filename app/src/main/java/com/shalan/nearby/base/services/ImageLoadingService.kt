package com.shalan.nearby.base.services

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

interface ImageLoadingService {

    fun loadDrawable(context: Context, drawable: Drawable, view: ImageView)

    fun loadCenterCropImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadFitCenterCropImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadImageWithPlaceholderCenterCropRoundedCorner(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		radius: Int,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholder(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholderFromFile(
		context: Context,
		imageUrl: String,
		placeholder: Drawable,
		view: ImageView
	)

    fun loadCircleImageWithPlaceholderFromUri(
		context: Context,
		imageUrl: Uri,
		placeholder: Drawable,
		view: ImageView
	)
}
