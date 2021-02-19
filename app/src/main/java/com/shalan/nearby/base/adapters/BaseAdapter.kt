package com.shalan.nearby.base.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shalan.nearby.base.viewholder.BaseViewHolder


abstract class BaseAdapter<T, VH : BaseViewHolder<*, T>>(
	diffUtil: DiffUtil.ItemCallback<T>,
	private val listener: ItemListener<T>? = null
) : ListAdapter<T, VH>(diffUtil), com.shalan.nearby.base.viewholder.ItemListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.baseBinding(getItem(position))
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onItemClicked(position: Int) {
        listener?.onItemClicked(getItem(position))
    }
}
