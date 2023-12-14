package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

class LoadUserTestsUseCase(private val testsRepository: TestsRepository) {
    suspend fun execute(user: User): List<TestBody> {
        return testsRepository.loadUserTests(user)?: listOf()
    }
}