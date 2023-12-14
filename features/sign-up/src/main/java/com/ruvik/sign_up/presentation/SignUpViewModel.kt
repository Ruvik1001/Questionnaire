package com.ruvik.sign_up.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.data.UserAuthData
import com.ruvik.domain.auth.usecase.SignUpUseCase
import com.ruvik.sign_up.R

/**
 * ViewModel for the sign-up screen.
 *
 * @property context The application context.
 * @property signUpUseCase The use case for signing up.
 */
class SignUpViewModel(
    private val context: Context,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private var user: UserAuthData = UserAuthData("", "")

    /**
     * Sets the user data for sign-up.
     *
     * @param email The user's email.
     * @param password The user's password.
     */
    fun setUserData(email: String, password: String) {
        user.email = email
        user.password = password
    }

    /**
     * Gets the user data for sign-up.
     *
     * @return The user data.
     */
    fun getUserData(): UserAuthData = user

    /**
     * Attempts to sign up the user.
     *
     * @return `true` if sign-up is successful, `false` otherwise.
     */
    suspend fun signUp(): Boolean = signUpUseCase.execute(user)

    /**
     * Validates the user's email format.
     *
     * @return `true` if the email is valid, `false` otherwise.
     */
    fun validateMail(): Boolean {
        val emailRegex = context.getString(R.string.email_pattern)
        return user.email.matches(emailRegex.toRegex())
    }

    /**
     * Validates the user's password length.
     *
     * @return `true` if the password meets the length requirement, `false` otherwise.
     */
    fun validatePassword(): Boolean {
        return user.password.length >= 8
    }
}
