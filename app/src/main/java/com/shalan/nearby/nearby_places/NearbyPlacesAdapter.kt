package com.shalan.nearby.nearby_places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.shalan.nearby.R
import com.shalan.nearby.base.adapters.BaseAdapter
import com.shalan.nearby.databinding.RecommendationItemViewBinding
import com.shalan.nearby.network.response.GroupItem

/**
 * Created by Mohamed Shalan on 2/20/21
 */

class NearbyPlacesAdapter : BaseAdapter<GroupItem, NearbyPlacesViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GroupItem>() {
            override fun areItemsTheSame(oldItem: GroupItem, newItem: GroupItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GroupItem, newItem: GroupItem): Boolean =
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