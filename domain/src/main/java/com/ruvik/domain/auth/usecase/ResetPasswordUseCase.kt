package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

class ResetPasswordUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(email: String): Boolean {
        return try {
            val methods = authRepository.fetchSignInMethodsForEmail(email)
            if (methods.signInMethods?.isEmpty() == true) {
                false
            } else {
                val task = authRepository.resetPassword(UserAuthData(email, ""))
                task.await()
                true
            }
        } catch (e: Exception) {
            false
        }
    }

}
