package com.ruvik.data.auth.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

/**
 * Implementation of [AuthRepository] for Firebase authentication.
 */
class AuthRepositoryImplData : AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Fetches the sign-in methods available for the specified email.
     *
     * @param email The email address for which to fetch sign-in methods.
     * @return The result of the sign-in methods query.
     */
    override suspend fun fetchSignInMethodsForEmail(email: String): SignInMethodQueryResult {
        return firebaseAuth.fetchSignInMethodsForEmail(email).await()
    }

    /**
     * Attempts to sign in the user with the provided [UserAuthData].
     *
     * @param user The user authentication data containing email and password.
     * @return A [Task] representing the result of the sign-in operation.
     */
    override suspend fun signIn(user: UserAuthData): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
    }

    /**
     * Attempts to sign up a new user with the provided [UserAuthData].
     *
     * @param user The user authentication data containing email and password.
     * @return A [Task] representing the result of the sign-up operation.
     */
    override suspend fun signUp(user: UserAuthData): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    /**
     * Sends a password reset email to the specified email address.
     *
     * @param user The user authentication data containing the email to reset the password for.
     * @return A [Task] representing the result of the password reset operation.
     */
    override suspend fun resetPassword(user: UserAuthData): Task<Void> {
        return firebaseAuth.sendPasswordResetEmail(user.email)
    }
}
