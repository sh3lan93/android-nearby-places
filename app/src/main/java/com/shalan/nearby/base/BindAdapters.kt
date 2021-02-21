package com.shalan.nearby.base

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalan.nearby.base.adapters.BaseAdapter
import com.shalan.nearby.base.services.ImageLoadingService
import com.shalan.nearby.base.utils.rx.IoTransformers
import com.shalan.nearby.network.services.NearbyService
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit


@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: BaseAdapter<*, *>) {
    view.adapter = adapter
}

@BindingAdapter(value = ["venueId", "placeholder", "imageLoading", "service"], requireAll = true)
fun bindImage(
    view: ImageView,
    venueId: String,
    placeholder: Drawable,
    imageLoading: ImageLoadingService,
    service: NearbyService
) {
    //I HAVE TO ADD A TIMER FOR API RESTRICTION AND QUOTA LIMIT
    Single.timer(5, TimeUnit.SECONDS)
        .flatMap {
            service.getVenuePhotos(venueId = venueId)
        }.compose(IoTransformers())
        .map {
            it.response.photos.items.firstOrNull()?.getFullPhotoUrl() ?: ""
        }
        .subscribe({ fullImageUrl ->
            imageLoading.loadImageWithPlaceholder(
                view.context,
                fullImageUrl,
                placeholder,
                view
            )
        }, {
            Log.e("BindImage", "bindImage: ", it)
            if (view.drawable == null)
                imageLoading.loadDrawable(view.context, placeholder, view)
        })
}
