package com.ruvik.domain.testmanagement

import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

/**
 * Interface defining operations related to Firebase services for test management.
 */
interface FirebaseService {

    /**
     * Loads tests associated with a user from Firebase.
     *
     * @param userEmail The email address of the user.
     * @return A list of TestBody objects representing tests associated with the user.
     */
    suspend fun loadUserTests(userEmail: String): List<TestBody>?

    /**
     * Adds a new test to Firebase.
     *
     * @param test The TestBody object representing the new test.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun addNewTest(test: TestBody): Boolean

    /**
     * Adds an answer to a test in Firebase.
     *
     * @param testResult The TestResult object representing the user's answers.
     * @return `true` if the operation is successful, `false` otherwise.
     */
    suspend fun addAnswer(testResult: TestResult): Boolean

    /**
     * Loads test results from Firebase.
     *
     * @param testHashCode The hash code of the test for which results are requested.
     * @return A list of TestResult objects representing results for the specified test.
     */
    suspend fun loadTestResults(testHashCode: String): List<TestResult>?

    /**
     * Finds a test by its hash code in Firebase.
     *
     * @param testHashCode The hash code of the test to be found.
     * @return The TestBody object representing the found test, or `null` if not found.
     */
    suspend fun findTestByHashCode(testHashCode: String): TestBody?
}
