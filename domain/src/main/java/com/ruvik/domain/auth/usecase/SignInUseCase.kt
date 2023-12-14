package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

/**
 * Use case for signing in a user.
 *
 * @property authRepository The repository providing authentication-related operations.
 */
class SignInUseCase(private val authRepository: AuthRepository) {

    /**
     * Executes the use case to sign in a user.
     *
     * @param userAuthData The user authentication data containing email and password.
     * @return `true` if the sign-in is successful, `false` otherwise.
     */
    suspend fun execute(userAuthData: UserAuthData): Boolean {
        return try {
            // Attempt to sign in the user
            val task = authRepository.signIn(userAuthData)
            task.await()
            true
        } catch (e: Exception) {
            // Handle exceptions and return false if sign-in fails
            false
        }
    }
}
