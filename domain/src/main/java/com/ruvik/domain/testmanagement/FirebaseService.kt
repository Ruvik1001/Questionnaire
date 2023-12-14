package com.ruvik.domain.testmanagement

import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

interface FirebaseService {
    suspend fun loadUserTests(userEmail: String): List<TestBody>?
    suspend fun addNewTest(test: TestBody): Boolean
    suspend fun addAnswer(testResult: TestResult): Boolean
    suspend fun loadTestResults(testHashCode: String): List<TestResult>?
    suspend fun findTestByHashCode(testHashCode: String): TestBody?
}
