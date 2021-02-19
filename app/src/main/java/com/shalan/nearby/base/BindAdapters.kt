package com.shalan.nearby.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalan.nearby.base.adapters.BaseAdapter
import com.shalan.nearby.base.services.ImageLoadingService


@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: BaseAdapter<*, *>) {
    view.adapter = adapter
}

@BindingAdapter(value = ["imgUrl", "placeholder", "imageLoading", "circle"], requireAll = false)
fun bindImage(
	view: ImageView,
	imageUrl: String?,
	placeholder: Drawable,
	imageLoadingService: ImageLoadingService,
	circle: Boolean = false
) {
    if (circle) {
        imageLoadingService.loadCircleImageWithPlaceholder(
			context = view.context,
			imageUrl = imageUrl ?: "",
			placeholder = placeholder,
			view = view
		)
    } else {
        imageLoadingService.loadCenterCropImageWithPlaceholder(
			context = view.context,
			imageUrl = imageUrl ?: "",
			placeholder = placeholder,
			view = view
		)
    }
}
