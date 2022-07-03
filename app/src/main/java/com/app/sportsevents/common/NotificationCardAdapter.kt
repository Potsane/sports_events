package com.app.sportsevents.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.sportsevents.R
import com.app.sportsevents.BR
import com.app.sportsevents.databinding.ItemSportEventCardBinding
import com.app.sportsevents.network.entity.SportEvent

class NotificationCardAdapter(
    private var notificationCards: MutableList<SportEvent>,
    private val listener: SportEventCardClickListener
) : RecyclerView.Adapter<NotificationCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSportEventCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sport_event_card,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<SportEvent>) {
        notificationCards.clear()
        notificationCards.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = notificationCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: SportEvent = notificationCards[position]
        holder.bind(item)
    }

    inner class ViewHolder(
        val itemView: View,
        val binding: ItemSportEventCardBinding
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(notificationCard: SportEvent) {
            binding.setVariable(BR.item, notificationCard)
            binding.setVariable(BR.clickListener, listener)
            binding.executePendingBindings()
        }
    }
}