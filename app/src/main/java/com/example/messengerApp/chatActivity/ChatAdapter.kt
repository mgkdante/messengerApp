package com.example.messengerApp.chatActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerApp.R
import com.example.messengerApp.chatActivity.MessageItemUi.Companion.USER_ONE_MESSAGE
import com.example.messengerApp.chatActivity.MessageItemUi.Companion.USER_TWO_MESSAGE

class ChatAdapter(var data: MutableList<MessageItemUi>) : RecyclerView.Adapter<MessageViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder<*> {
        val context = parent.context
        return when (viewType) {
            USER_ONE_MESSAGE -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.user1_message, parent, false)
                MessageUserOneViewHolder(view)
            }
            USER_TWO_MESSAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user2_message, parent, false)
                MessageUserTwoViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder<*>, position: Int) {
        val item = data[position]
        Log.d("adapter View", position.toString() + item.content)
        when (holder) {
            is MessageUserOneViewHolder -> holder.bind(item)
            is MessageUserTwoViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].messageType

   inner class MessageUserOneViewHolder(view: View) : MessageViewHolder<MessageItemUi>(view) {
        private val messageContent = view.findViewById<TextView>(R.id.message_user_one)
        private val messageImage = view.findViewById<ImageView>(R.id.image_user_one)

        override fun bind(item: MessageItemUi) {
                messageContent.text = item.content
                messageImage.setImageURI(item.media)

            if(item.content == ""){
                messageContent.isGone = true
            }
            if(item.media == null){
                messageImage.isGone = true
            }
        }
    }

    inner class MessageUserTwoViewHolder(view: View) : MessageViewHolder<MessageItemUi>(view) {
        private val messageContent = view.findViewById<TextView>(R.id.message_user_two)
        private val messageImage = view.findViewById<ImageView>(R.id.image_user_two)


        override fun bind(item: MessageItemUi) {
            messageContent.text = item.content
            messageImage.setImageURI(item.media)

            if(item.content == ""){
                messageContent.isGone = true
            }
            if(item.media == null){
                messageImage.isGone = true
            }
        }

    }

}