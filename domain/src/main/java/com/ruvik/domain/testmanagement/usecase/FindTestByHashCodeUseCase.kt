package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

/**
 * Use case for finding a test by its hash code.
 *
 * @param testsRepository The repository providing access to test-related operations.
 */
class FindTestByHashCodeUseCase(private val testsRepository: TestsRepository) {

    /**
     * Executes the use case to find a test by its hash code.
     *
     * @param hashCode The hash code of the test to be found.
     * @return The TestBody object representing the details of the found test, or `null` if not found.
     */
    suspend fun execute(hashCode: String): TestBody? {
        return testsRepository.getTestByHashCode(hashCode)
    }
}
