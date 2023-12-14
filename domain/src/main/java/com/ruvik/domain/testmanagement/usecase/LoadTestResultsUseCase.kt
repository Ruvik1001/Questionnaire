package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

class LoadTestResultsUseCase(private val testsRepository: TestsRepository) {
    suspend fun execute(testHashCode: String): List<TestResult> {
        return testsRepository.loadTestResults(testHashCode)?: listOf()
    }
}