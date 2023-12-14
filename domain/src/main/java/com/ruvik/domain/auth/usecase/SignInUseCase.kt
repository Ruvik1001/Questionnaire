package com.ruvik.domain.auth.usecase

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.data.UserAuthData
import kotlinx.coroutines.tasks.await

class SignInUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(userAuthData: UserAuthData): Boolean {
        return try {
            val task = authRepository.signIn(userAuthData)
            task.await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
