package com.ruvik.forgot_password.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.usecase.ResetPasswordUseCase
import com.ruvik.forgot_password.R

class ForgotPasswordViewModel(
    private val context: Context,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {
    private var email: String = ""

    fun getEmail(): String = email
    fun setEmail(email: String) {
        this.email = email
    }

    suspend fun resetPassword(): Boolean = resetPasswordUseCase.execute(email)

    fun validateMail(): Boolean {
        val emailRegex = context.getString(R.string.email_pattern)
        return email.matches(emailRegex.toRegex())
    }
}