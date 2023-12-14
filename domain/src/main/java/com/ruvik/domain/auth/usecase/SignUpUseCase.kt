package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

class SignUpUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(userAuthData: UserAuthData): Boolean {
        return try {
            val task = authRepository.signUp(userAuthData)
            task.await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
