package com.mynewsapp.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val currentUserLiveData = MutableLiveData<FirebaseUser>()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var loading by mutableStateOf(false)

    init {
        // Initialize the ViewModel by observing the Firebase A
        auth.addAuthStateListener { firebaseAuth ->
            currentUserLiveData.value = firebaseAuth.currentUser
        }
    }

    fun getCurrentUserLiveData(): LiveData<FirebaseUser> {
        return currentUserLiveData
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun signIn(onSuccess: () -> Unit, onFailed: (err: String?) -> Unit) {
        if (!isValidEmail(email)) {
            onFailed("Invalid email format")
            return
        }

        // Validation for password
        if (password.isEmpty() || password.length < 6) {
            onFailed("Password must be at least 6 characters long")
            return
        }
        loading = true
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener()
        { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                loading = false
                onSuccess()
            } else {
                loading = false
                onFailed("User wasn't found, please check credentials and try again")
            }
        }
    }

    fun signUp(onSuccess: () -> Unit = {}, onFailed: (err: String?) -> Unit) {
        if (!isValidEmail(email)) {
            onFailed("Invalid email format")
            return
        }

        // Validation for password
        if (password.isEmpty() || password.length < 6) {
            onFailed("Password must be at least 6 characters long")
            return
        }
        loading = true
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){
            task ->
            if (task.isSuccessful) {
                onSuccess()
                loading = false
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                onFailed("Could not create account, please try again")
                loading = false
            }
        }
    }
}