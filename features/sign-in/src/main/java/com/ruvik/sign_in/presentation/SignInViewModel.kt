package com.ruvik.sign_in.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.data.UserAuthData
import com.ruvik.domain.auth.usecase.SignInUseCase
import com.ruvik.sign_in.SignInRouter

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val signInRouter: SignInRouter
) : ViewModel() {
    private var user: UserAuthData = UserAuthData("", "")

    fun setUserData(email: String, password: String) {
        user.email = email
        user.password = password
    }

    fun getUserData(): UserAuthData = user

    suspend fun signIn(): Boolean = signInUseCase.execute(user)

    fun lunchProfile() {
        signInRouter.goToMyTests()
    }
    fun lunchSignUp() {
        signInRouter.goToSignUp()
    }
    fun lunchForgotPassword() {
        signInRouter.goToForgotPassword()
    }

}