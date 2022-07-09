package com.app.sportsevents.common

import androidx.recyclerview.widget.DiffUtil
import com.app.sportsevents.network.entity.SportEvent

class SportEventListDiffUtil(
    private val currentItems: List<SportEvent>,
    private val updatedItems: List<SportEvent>
) : DiffUtil.Callback() {

    override fun getOldListSize() = currentItems.size

    override fun getNewListSize() = updatedItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentItems[oldItemPosition].id == updatedItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            currentItems[oldItemPosition].title != updatedItems[newItemPosition].title -> false
            currentItems[oldItemPosition].subtitle != updatedItems[newItemPosition].subtitle -> false
            currentItems[oldItemPosition].imageUrl != updatedItems[newItemPosition].imageUrl -> false
            currentItems[oldItemPosition].videoUrl != updatedItems[newItemPosition].videoUrl -> false
            currentItems[oldItemPosition].date != updatedItems[newItemPosition].date -> false
            else -> true
        }
    }
}