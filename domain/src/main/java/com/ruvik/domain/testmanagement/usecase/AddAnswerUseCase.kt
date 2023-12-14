package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

class AddAnswerUseCase(private val testsRepository: TestsRepository) {
    suspend fun execute(testResult: TestResult): Boolean {
        return testsRepository.addAnswer(testResult)
    }
}