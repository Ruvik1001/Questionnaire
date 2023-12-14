package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

/**
 * Use case for signing up a new user.
 *
 * @property authRepository The repository providing authentication-related operations.
 */
class SignUpUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the use case to sign up a new user.
     *
     * @param userAuthData The user authentication data containing email and password.
     * @return `true` if the sign-up is successful, `false` otherwise.
     */
    suspend fun execute(userAuthData: UserAuthData): Boolean {
        return try {
            // Attempt to sign up the new user
            val task = authRepository.signUp(userAuthData)
            task.await()
            true
        } catch (e: Exception) {
            // Handle exceptions and return false if sign-up fails
            false
        }
    }
}
