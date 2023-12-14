package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

/**
 * Use case for resetting the password associated with a user's email address.
 *
 * @property authRepository The repository providing authentication-related operations.
 */
class ResetPasswordUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the use case to reset the password.
     *
     * @param email The user's email address for which the password should be reset.
     * @return `true` if the password reset is successful, `false` otherwise.
     */
    suspend fun execute(email: String): Boolean {
        return try {
            // Fetch sign-in methods for the email to check if the user exists
            val methods = authRepository.fetchSignInMethodsForEmail(email)

            if (methods.signInMethods?.isEmpty() == true) {
                // User does not exist
                false
            } else {
                // User exists, initiate password reset
                val task = authRepository.resetPassword(UserAuthData(email, ""))
                task.await()
                true
            }
        } catch (e: Exception) {
            // Handle exceptions and return false if an error occurs
            false
        }
    }
}
