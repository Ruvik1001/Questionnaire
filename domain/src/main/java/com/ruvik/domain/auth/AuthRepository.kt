package com.ruvik.domain.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.SignInMethodQueryResult
import com.ruvik.domain.auth.data.UserAuthData


interface AuthRepository {
    suspend fun fetchSignInMethodsForEmail(email: String): SignInMethodQueryResult
    suspend fun signIn(user: UserAuthData): Task<AuthResult>
    suspend fun signUp(user: UserAuthData): Task<AuthResult>
    suspend fun resetPassword(user: UserAuthData): Task<Void>
}