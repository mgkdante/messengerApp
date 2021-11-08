package com.example.messengerApp.chatActivity

import android.view.View
import android.widget.TextView
import com.example.messengerApp.R

class MessageUserOneViewHolder(private val view: View) : MessageViewHolder<MessageItemUi>(view) {
    private val messageContent = view.findViewById<TextView>(R.id.message_user_one)

    override fun bind(item: MessageItemUi) {
        messageContent.text = item.content
    }


}
