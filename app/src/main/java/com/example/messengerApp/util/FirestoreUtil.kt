package com.example.messengerApp.util

import com.example.messengerApp.users.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.NullPointerException

object FirestoreUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val currentUserDocRef: DocumentReference
    get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid
        ?: throw NullPointerException("UID is null.")}")

    fun initCurrentUserIfFirstTime(onComplete: () ->Unit){
        currentUserDocRef.get().addOnSuccessListener { documentSnapchot ->
            if(!documentSnapchot.exists()){
                val newUser = User(FirebaseAuth.getInstance().currentUser?.displayName ?: "")
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }
            else
                onComplete()
        }
    }
    fun updateCurrentUser(name: String = ""){
        val userFieldMap = mutableMapOf<String, Any>()
        if(name.isNotBlank())
            userFieldMap["name"] = name
        currentUserDocRef.update(userFieldMap)
    }
    fun getCurrentUser(onComplete: (User) -> Unit){
        currentUserDocRef.get()
            .addOnSuccessListener {
                it.toObject(User::class.java)?.let { it1 -> onComplete(it1) }
            }
    }
}