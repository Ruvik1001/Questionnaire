package com.ruvik.domain.testmanagement.usecase

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody

/**
 * Use case for loading tests associated with a user.
 *
 * @param testsRepository The repository providing access to test-related operations.
 */
class LoadUserTestsUseCase(private val testsRepository: TestsRepository) {

    /**
     * Executes the use case to load tests associated with a user.
     *
     * @param user The user for whom tests are to be loaded.
     * @return A list of TestBody objects representing the tests associated with the user, or an empty list if none are found.
     */
    suspend fun execute(user: User): List<TestBody> {
        return testsRepository.loadUserTests(user) ?: listOf()
    }
}
