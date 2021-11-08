package com.example.messengerApp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.*


class MainActivity : AppCompatActivity() {

    private val cols = listOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    ).toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar2))

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                Array(1) { Manifest.permission.READ_CONTACTS },
                111
            )
        } else
            readContact()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            readContact()
    }

    private fun readContact() {

        val from = listOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()

        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        val rs = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        val adapter =
            SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, rs, from, to, 0)


        val listView: ListView = findViewById(R.id.list_view)

        listView.adapter = adapter

       val searchView: SearchView = findViewById(R.id.search)

       searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                val rs = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    cols,
                    "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?",
                    Array(1) { "%$p0%" },
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )
                adapter.changeCursor(rs)
                return false
            }
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val contactName: TextView = view.findViewById(android.R.id.text1)
            val nameString = contactName.text.toString()

            val contactNumber: TextView = view.findViewById(android.R.id.text2)
            val numberString = contactNumber.text.toString()

            val intent = Intent(view.context, ChatActivity::class.java)
            intent.putExtra("name", nameString)
            intent.putExtra("number", numberString)
            startActivity(intent)
        }
    }

}