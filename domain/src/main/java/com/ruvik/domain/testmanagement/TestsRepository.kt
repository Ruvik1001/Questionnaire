package com.ruvik.domain.testmanagement

import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

/**
 * Interface defining operations related to tests repository.
 */
interface TestsRepository {

    /**
     * Loads tests associated with a user.
     *
     * @param user The User object representing the user.
     * @return A list of TestBody objects representing tests associated with the user.
     */
    suspend fun loadUserTests(user: User): List<TestBody>?

    /**
     * Adds a new test.
     *
     * @param test The TestBody object representing the new test.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun addNewTest(test: TestBody): Boolean

    /**
     * Adds an answer to a test.
     *
     * @param testResult The TestResult object representing the user's answers.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun addAnswer(test: TestResult): Boolean

    /**
     * Loads test results.
     *
     * @param testHashCode The hash code of the test for which results are requested.
     * @return A list of TestResult objects representing results for the specified test.
     */
    suspend fun loadTestResults(testHashCode: String): List<TestResult>?

    /**
     * Gets a test by its hash code.
     *
     * @param testHashCode The hash code of the test to be retrieved.
     * @return The TestBody object representing the retrieved test, or `null` if not found.
     */
    suspend fun getTestByHashCode(testHashCode: String): TestBody?
}
