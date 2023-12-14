package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

/**
 * Use case for loading test results.
 *
 * @param testsRepository The repository providing access to test-related operations.
 */
class LoadTestResultsUseCase(private val testsRepository: TestsRepository) {

    /**
     * Executes the use case to load test results for a given test hash code.
     *
     * @param testHashCode The hash code of the test for which results are to be loaded.
     * @return A list of TestResult objects representing the results of the test, or an empty list if none are found.
     */
    suspend fun execute(testHashCode: String): List<TestResult> {
        return testsRepository.loadTestResults(testHashCode) ?: listOf()
    }
}
