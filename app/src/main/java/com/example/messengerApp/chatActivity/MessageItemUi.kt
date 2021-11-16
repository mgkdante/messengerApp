package com.example.messengerApp.chatActivity

import android.net.Uri

class MessageItemUi(val content:String, val media: Uri?, val messageType:Int){
    companion object {
        const val USER_ONE_MESSAGE = 0
        const val USER_TWO_MESSAGE = 1
    }
}