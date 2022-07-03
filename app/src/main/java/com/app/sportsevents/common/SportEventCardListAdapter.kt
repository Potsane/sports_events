package com.app.sportsevents.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.sportsevents.R
import com.app.sportsevents.BR
import com.app.sportsevents.databinding.ItemSportEventCardBinding
import com.app.sportsevents.network.entity.SportEvent

class SportEventCardListAdapter(
    private val items: MutableList<SportEvent>,
    private val listener: SportEventCardClickListener
) : RecyclerView.Adapter<SportEventCardListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val binding: ItemSportEventCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sport_event_card,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    fun updateItems(updatedList: List<SportEvent>) {
        items.clear()
        items.addAll(updatedList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SportEventCardListAdapter.ViewHolder, position: Int) {
        val item : SportEvent = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(
         val view: View,
         val binding: ItemSportEventCardBinding
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: SportEvent) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.clickListener, listener)
            binding.executePendingBindings()
        }
    }
}
