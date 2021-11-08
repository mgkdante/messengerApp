package com.example.messengerApp.chatActivity

import android.view.View
import android.widget.TextView
import com.example.messengerApp.R

class MessageUserTwoViewHolder(private val view: View) : MessageViewHolder<MessageItemUi>(view) {
    private val messageContent = view.findViewById<TextView>(R.id.message_user_two)

    override fun bind(item: MessageItemUi) {
        messageContent.text = item.content
    }

}