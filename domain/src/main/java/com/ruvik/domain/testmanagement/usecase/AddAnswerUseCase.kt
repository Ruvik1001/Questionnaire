package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

/**
 * Use case for adding an answer to a test.
 *
 * @param testsRepository The repository providing access to test-related operations.
 */
class AddAnswerUseCase(private val testsRepository: TestsRepository) {

    /**
     * Executes the use case to add an answer to a test.
     *
     * @param testResult The TestResult object representing the user's answers.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun execute(testResult: TestResult): Boolean {
        return testsRepository.addAnswer(testResult)
    }
}
