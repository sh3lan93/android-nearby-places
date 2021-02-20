package com.shalan.nearby.nearby_places

import com.shalan.nearby.base.viewholder.BaseViewHolder
import com.shalan.nearby.databinding.RecommendationItemViewBinding
import com.shalan.nearby.network.response.Venue

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesViewHolder(private val binding: RecommendationItemViewBinding) :
    BaseViewHolder<RecommendationItemViewBinding, Venue>(binding) {

    override fun bind(item: Venue) {
        binding.venue = item
        binding.executePendingBindings()
    }
}