package com.example.messengerApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.MenuItem

import android.widget.TextView

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.app_bar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);


        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")

        title = name

        val textView2 = findViewById<TextView>(R.id.text_view2).apply {
            text = number
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
