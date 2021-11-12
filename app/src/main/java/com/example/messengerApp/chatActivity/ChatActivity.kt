package com.example.messengerApp.chatActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerApp.R

class ChatActivity : AppCompatActivity() {
    private val data = mutableListOf<MessageItemUi>()
    private val adapter = ChatAdapter(data)
    private lateinit var textView: TextView
    private lateinit var button2: Button
    private lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null


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

        button2 = findViewById(R.id.button2)

        textView = findViewById(R.id.input_message)

        val recyclerView= findViewById<RecyclerView>(R.id.message)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
        }


        button = findViewById(R.id.button)
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


        button2.setOnClickListener{
            insertItem()
            textView.text = ""
            recyclerView.scrollToPosition(0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
        }
    }

    private fun insertItem(){
        val newItem: MessageItemUi = MessageItemUi(textView.text.toString(),
            0)
        data.add(0,newItem)
        adapter.notifyItemInserted(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
