package com.ruvik.sign_in.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.data.UserAuthData
import com.ruvik.domain.auth.usecase.SignInUseCase
import com.ruvik.sign_in.SignInRouter

/**
 * ViewModel for the sign-in screen.
 *
 * @property signInUseCase The use case for signing in.
 * @property signInRouter The router for navigating between screens in the sign-in flow.
 */
class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val signInRouter: SignInRouter
) : ViewModel() {
    private var user: UserAuthData = UserAuthData("", "")

    /**
     * Sets the user data for sign-in.
     *
     * @param email The user's email.
     * @param password The user's password.
     */
    fun setUserData(email: String, password: String) {
        user.email = email
        user.password = password
    }

    /**
     * Gets the user data for sign-in.
     *
     * @return The user data.
     */
    fun getUserData(): UserAuthData = user

    /**
     * Attempts to sign in the user.
     *
     * @return `true` if sign-in is successful, `false` otherwise.
     */
    suspend fun signIn(): Boolean = signInUseCase.execute(user)

    /**
     * Navigates to the user's tests screen after successful sign-in.
     */
    fun lunchProfile() {
        signInRouter.goToMyTests()
    }

    /**
     * Navigates to the sign-up screen.
     */
    fun lunchSignUp() {
        signInRouter.goToSignUp()
    }

    /**
     * Navigates to the forgot password screen.
     */
    fun lunchForgotPassword() {
        signInRouter.goToForgotPassword()
    }
}
