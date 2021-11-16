package com.example.messengerApp.authenticationActivity

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.messengerApp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var reset: Button
    private lateinit var email: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        auth = Firebase.auth

        setSupportActionBar(findViewById(R.id.app_bar_reset)).apply {
            title = "Reset Password"
        }

        reset = findViewById(R.id.reset_password)
        email = findViewById(R.id.email_reset)

        reset.setOnClickListener {
            resetEmail(email.text.toString())
        }
    }

    private fun resetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "We sent a reset password email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show()
                }
            }
    }
}