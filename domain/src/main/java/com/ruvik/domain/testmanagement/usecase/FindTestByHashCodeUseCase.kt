package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

class FindTestByHashCodeUseCase(private val testsRepository: TestsRepository) {
    suspend fun execute(hashCode: String): TestBody? {
        return testsRepository.getTestByHashCode(hashCode)
    }
}