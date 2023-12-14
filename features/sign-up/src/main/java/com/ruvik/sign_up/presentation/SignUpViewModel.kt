package com.ruvik.sign_up.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.data.UserAuthData
import com.ruvik.domain.auth.usecase.SignUpUseCase
import com.ruvik.sign_up.R

class SignUpViewModel(
    private val context: Context,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private var user: UserAuthData = UserAuthData("", "")

    fun setUserData(email: String, password: String) {
        user.email = email
        user.password = password
    }

    fun getUserData(): UserAuthData = user

    suspend fun signUp(): Boolean = signUpUseCase.execute(user)

    fun validateMail(): Boolean {
        val emailRegex = context.getString(R.string.email_pattern)
        return user.email.matches(emailRegex.toRegex())
    }

    fun validatePassword(): Boolean {
        return user.password.length >= 8
    }

}