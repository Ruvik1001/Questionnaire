package com.ruvik.forgot_password.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ruvik.domain.auth.usecase.ResetPasswordUseCase
import com.ruvik.forgot_password.R

/**
 * ViewModel for handling password reset functionality.
 *
 * @param context The application context.
 * @param resetPasswordUseCase The use case for resetting a user's password.
 */
class ForgotPasswordViewModel(
    private val context: Context,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {
    private var email: String = ""

    /**
     * Gets the current email.
     *
     * @return The current email.
     */
    fun getEmail(): String = email

    /**
     * Sets the email for password reset.
     *
     * @param email The email to be set.
     */
    fun setEmail(email: String) {
        this.email = email
    }

    /**
     * Initiates the password reset process.
     *
     * @return `true` if the password reset is successful, `false` otherwise.
     */
    suspend fun resetPassword(): Boolean = resetPasswordUseCase.execute(email)

    /**
     * Validates the email using a regular expression.
     *
     * @return `true` if the email is valid, `false` otherwise.
     */
    fun validateMail(): Boolean {
        val emailRegex = context.getString(R.string.email_pattern)
        return email.matches(emailRegex.toRegex())
    }
}
