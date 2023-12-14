package com.ruvik.data.testmanagement.repository.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import kotlinx.coroutines.tasks.await

/**
 * Implementation of the [FirebaseService] interface for managing tests and test results in Firebase.
 */
class FirebaseServiceImpl : FirebaseService {

    private val database = FirebaseDatabase.getInstance()

    /**
     * Retrieves the list of tests associated with the specified user email.
     *
     * @param userEmail The email address of the user.
     * @return A list of [TestBody] objects associated with the user, or null if no tests are found.
     */
    override suspend fun loadUserTests(userEmail: String): List<TestBody>? {
        val userTestsRef = database.reference.child("Tests").child(userEmail.replace(".", "_"))
        val dataSnapshot = userTestsRef.get().await()

        val tests = dataSnapshot.getValue(object : GenericTypeIndicator<Map<String, TestBody>>() {})
        return tests?.values?.toList()
    }

    /**
     * Adds a new test to the Firebase database.
     *
     * @param test The [TestBody] object representing the test.
     * @return True if the test is added successfully, false otherwise.
     */
    override suspend fun addNewTest(test: TestBody): Boolean {
        val userTestsRef = database.reference.child("Tests")
            .child(test.author.replace(".", "_"))
        val testRef = userTestsRef.child(test.hashCode)

        return try {
            testRef.setValue(test).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Adds a test result to the Firebase database.
     *
     * @param testResult The [TestResult] object representing the test result.
     * @return True if the test result is added successfully, false otherwise.
     */
    override suspend fun addAnswer(testResult: TestResult): Boolean {
        val answersRef = database.reference.child("Answers").child(testResult.hashCode)
            .child(testResult.user.replace(".", "_"))
        val answerRef = answersRef.push()

        return try {
            answerRef.setValue(testResult).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Retrieves the list of test results for a specific test.
     *
     * @param testHashCode The hash code of the test.
     * @return A list of [TestResult] objects for the specified test, or null if no results are found.
     */
    override suspend fun loadTestResults(testHashCode: String): List<TestResult>? {
        val answersRef = database.reference.child("Answers").child(testHashCode)
        val dataSnapshot = answersRef.get().await()

        val testResults: MutableList<TestResult> = mutableListOf()

        for (userSnapshot in dataSnapshot.children) {
            val user = userSnapshot.key // This will be the user
            for (resultSnapshot in userSnapshot.children) {
                val testResult = resultSnapshot.getValue(TestResult::class.java)
                if (testResult != null) {
                    testResults.add(testResult)
                }
            }
        }

        return if (testResults.isNotEmpty()) testResults else null
    }

    /**
     * Finds a test by its hash code.
     *
     * @param testHashCode The hash code of the test.
     * @return The [TestBody] object representing the test, or null if the test is not found.
     */
    override suspend fun findTestByHashCode(testHashCode: String): TestBody? {
        val testsRef = database.reference.child("Tests")
        val dataSnapshot = testsRef.get().await()

        for (userSnapshot in dataSnapshot.children) {
            val userTestsRef = userSnapshot.ref
            val testRef = userTestsRef.child(testHashCode)

            val testSnapshot = testRef.get().await()
            val testBody = testSnapshot.getValue(TestBody::class.java)

            if (testBody != null) {
                return testBody
            }
        }

        return null
    }
}
