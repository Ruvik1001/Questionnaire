package com.ruvik.data.auth.repository.impl

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.ruvik.domain.auth.data.UserAuthData
import com.ruvik.domain.auth.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImplData : AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun fetchSignInMethodsForEmail(email: String): SignInMethodQueryResult {
        return firebaseAuth.fetchSignInMethodsForEmail(email).await()
    }

    override suspend fun signIn(user: UserAuthData): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun signUp(user: UserAuthData): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun resetPassword(user: UserAuthData): Task<Void> {
        return firebaseAuth.sendPasswordResetEmail(user.email)
    }
}
