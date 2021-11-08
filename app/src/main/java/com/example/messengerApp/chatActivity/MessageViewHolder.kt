package com.example.messengerApp.chatActivity

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MessageViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}