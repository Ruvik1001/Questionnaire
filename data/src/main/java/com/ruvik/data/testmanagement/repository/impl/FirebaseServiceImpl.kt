package com.ruvik.data.testmanagement.repository.impl

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import kotlinx.coroutines.tasks.await

class FirebaseServiceImpl : FirebaseService {

    private val database = FirebaseDatabase.getInstance()

    override suspend fun loadUserTests(userEmail: String): List<TestBody>? {
        val userTestsRef = database.reference.child("Tests").child(userEmail.replace(".", "_"))
        val dataSnapshot = userTestsRef.get().await()

        val tests = dataSnapshot.getValue(object : GenericTypeIndicator<Map<String, TestBody>>() {})
        return tests?.values?.toList()

    }

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

    override suspend fun loadTestResults(testHashCode: String): List<TestResult>? {
        val answersRef = database.reference.child("Answers").child(testHashCode)
        val dataSnapshot = answersRef.get().await()

        val testResults: MutableList<TestResult> = mutableListOf()

        for (userSnapshot in dataSnapshot.children) {
            val user = userSnapshot.key // это будет User
            for (resultSnapshot in userSnapshot.children) {
                val testResult = resultSnapshot.getValue(TestResult::class.java)
                if (testResult != null) {
                    testResults.add(testResult)
                }
            }
        }

        return if (testResults.isNotEmpty()) testResults else null
    }




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
