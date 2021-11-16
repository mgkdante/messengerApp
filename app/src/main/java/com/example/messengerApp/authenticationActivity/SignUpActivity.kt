package com.example.messengerApp.authenticationActivity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.messengerApp.R
import com.example.messengerApp.listActivity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var register: Button
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var reset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(findViewById(R.id.app_bar_sign_up)).apply {
            title = "Sign Up"
        }
        auth = Firebase.auth

        email = findViewById(R.id.sign_up_email)
        password = findViewById(R.id.sign_up_password)
        register = findViewById(R.id.sign_up_resgister)

        register.setOnClickListener{
            createAccount(email.text.toString(), password.text.toString())
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}