package com.ruvik.domain.testmanagement

import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

interface TestsRepository {
    suspend fun loadUserTests(user: User): List<TestBody>?
    suspend fun addNewTest(test: TestBody): Boolean
    suspend fun addAnswer(test: TestResult): Boolean
    suspend fun loadTestResults(testHashCode: String): List<TestResult>?
    suspend fun getTestByHashCode(testHashCode: String): TestBody?
}