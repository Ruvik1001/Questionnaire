package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

class AddNewTestUseCase(private val testsRepository: TestsRepository) {
    suspend fun execute(testBody: TestBody): Boolean {
        return testsRepository.addNewTest(testBody)
    }
}