package com.ruvik.find_test.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.usecase.FindTestByHashCodeUseCase
import com.ruvik.find_test.FindTestRouter

class FindTestViewModel(
    private val findTestByHashCodeUseCase: FindTestByHashCodeUseCase,
    private val findTestRouter: FindTestRouter
): ViewModel() {
    private var testBody: TestBody? = null


    suspend fun findTest(hashCode: String): Boolean {
        testBody = findTestByHashCodeUseCase.execute(hashCode)
        return testBody != null
    }

    fun runTest(): Boolean = if (testBody == null) false else {
        findTestRouter.goToRunTest(testBody!!)
        true
    }

    fun getMetadata(): Pair<String, String> = Pair(testBody?.author?:"", testBody?.name?:"")

    fun goToMyTests() {
        findTestRouter.goToMyTests()
    }

}