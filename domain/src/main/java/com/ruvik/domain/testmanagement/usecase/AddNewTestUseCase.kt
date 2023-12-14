package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

/**
 * Use case for adding a new test.
 *
 * @param testsRepository The repository providing access to test-related operations.
 */
class AddNewTestUseCase(private val testsRepository: TestsRepository) {

    /**
     * Executes the use case to add a new test.
     *
     * @param testBody The TestBody object representing the details of the new test.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun execute(testBody: TestBody): Boolean {
        return testsRepository.addNewTest(testBody)
    }
}
