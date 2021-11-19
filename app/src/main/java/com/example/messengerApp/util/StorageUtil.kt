package com.example.messengerApp.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.NullPointerException

object StorageUtil {
    private val storageInstance: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
        private val currentUserRef: StorageReference
            get() = storageInstance.reference
                .child(FirebaseAuth.getInstance().uid
                    ?: throw NullPointerException("UID is null."))
}