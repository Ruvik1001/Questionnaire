package com.ruvik.data.testmanagement.repository.impl

import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult

class TestsRepositoryImpl(private val firebaseService: FirebaseService) : TestsRepository {

    override suspend fun loadUserTests(user: User): List<TestBody>? {
        return firebaseService.loadUserTests(user.email)
    }

    override suspend fun addNewTest(test: TestBody): Boolean {
        return firebaseService.addNewTest(test)
    }

    override suspend fun addAnswer(test: TestResult): Boolean {
        return firebaseService.addAnswer(test)
    }

    override suspend fun loadTestResults(testHashCode: String): List<TestResult>? {
        return firebaseService.loadTestResults(testHashCode)
    }

    override suspend fun getTestByHashCode(testHashCode: String): TestBody? {
        return firebaseService.findTestByHashCode(testHashCode)
    }

}
