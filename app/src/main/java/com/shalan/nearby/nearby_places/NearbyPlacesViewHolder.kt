package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.services.ImageLoadingService
import com.shalan.nearby.base.viewholder.BaseViewHolder
import com.shalan.nearby.databinding.RecommendationItemViewBinding
import com.shalan.nearby.network.response.Venue
import com.shalan.nearby.network.services.NearbyService
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesViewHolder(private val binding: RecommendationItemViewBinding) :
    BaseViewHolder<RecommendationItemViewBinding, Venue>(binding), KoinComponent {

    private val imageLoadingService by inject<ImageLoadingService>()
    private val nearbyService by inject<NearbyService>()

    override fun bind(item: Venue) {
        binding.ivPlaceImage.setImageResource(0)
        binding.venue = item
        binding.nearbyService = nearbyService
        binding.imageLoadingService = imageLoadingService
        binding.executePendingBindings()
    }
}