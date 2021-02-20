package com.shalan.nearby.nearby_places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.shalan.nearby.R
import com.shalan.nearby.base.adapters.BaseAdapter
import com.shalan.nearby.databinding.RecommendationItemViewBinding
import com.shalan.nearby.network.response.Venue

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesAdapter : BaseAdapter<Venue, NearbyPlacesViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Venue>() {
            override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean =
                oldItem == newItem
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): NearbyPlacesViewHolder =
        DataBindingUtil.inflate<RecommendationItemViewBinding>(
            LayoutInflater.from(parent.context), R.layout.recommendation_item_view, parent, false
        ).let {
            NearbyPlacesViewHolder(it)
        }
}