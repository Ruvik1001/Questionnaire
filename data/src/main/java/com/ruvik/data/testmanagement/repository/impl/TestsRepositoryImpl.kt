package com.ruvik.data.testmanagement.repository.impl

import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

/**
 * Implementation of the [TestsRepository] interface that delegates operations to [FirebaseService].
 *
 * @param firebaseService The [FirebaseService] instance responsible for interacting with Firebase.
 */
class TestsRepositoryImpl(private val firebaseService: FirebaseService) : TestsRepository {

    /**
     * Retrieves the list of tests associated with the specified user.
     *
     * @param user The [User] object representing the user.
     * @return A list of [TestBody] objects associated with the user, or null if no tests are found.
     */
    override suspend fun loadUserTests(user: User): List<TestBody>? {
        return firebaseService.loadUserTests(user.email)
    }

    /**
     * Adds a new test to the repository.
     *
     * @param test The [TestBody] object representing the test to be added.
     * @return True if the test is added successfully, false otherwise.
     */
    override suspend fun addNewTest(test: TestBody): Boolean {
        return firebaseService.addNewTest(test)
    }

    /**
     * Adds a test result to the repository.
     *
     * @param test The [TestResult] object representing the test result to be added.
     * @return True if the test result is added successfully, false otherwise.
     */
    override suspend fun addAnswer(test: TestResult): Boolean {
        return firebaseService.addAnswer(test)
    }

    /**
     * Retrieves the list of test results for a specific test.
     *
     * @param testHashCode The hash code of the test.
     * @return A list of [TestResult] objects for the specified test, or null if no results are found.
     */
    override suspend fun loadTestResults(testHashCode: String): List<TestResult>? {
        return firebaseService.loadTestResults(testHashCode)
    }

    /**
     * Retrieves a test by its hash code.
     *
     * @param testHashCode The hash code of the test.
     * @return The [TestBody] object representing the test, or null if the test is not found.
     */
    override suspend fun getTestByHashCode(testHashCode: String): TestBody? {
        return firebaseService.findTestByHashCode(testHashCode)
    }
}
