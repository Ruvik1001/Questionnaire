package com.ruvik.domain.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.SignInMethodQueryResult
import com.ruvik.domain.auth.data.UserAuthData

/**
 * Repository interface for authentication operations.
 */
interface AuthRepository {

    /**
     * Fetches the sign-in methods associated with the provided email.
     *
     * @param email The email for which sign-in methods are to be fetched.
     * @return The result of the query for sign-in methods.
     */
    suspend fun fetchSignInMethodsForEmail(email: String): SignInMethodQueryResult

    /**
     * Signs in a user using the provided authentication data.
     *
     * @param user The user authentication data containing email and password.
     * @return A task representing the asynchronous sign-in operation.
     */
    suspend fun signIn(user: UserAuthData): Task<AuthResult>

    /**
     * Signs up a new user using the provided authentication data.
     *
     * @param user The user authentication data containing email and password.
     * @return A task representing the asynchronous sign-up operation.
     */
    suspend fun signUp(user: UserAuthData): Task<AuthResult>

    /**
     * Sends a password reset email to the user associated with the provided email.
     *
     * @param user The user authentication data containing the email for password reset.
     * @return A task representing the asynchronous password reset operation.
     */
    suspend fun resetPassword(user: UserAuthData): Task<Void>
}
