package com.ruvik.run_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ruvik.common.DataTime
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import com.ruvik.domain.testmanagement.usecase.AddAnswerUseCase
import com.ruvik.run_test.RunTestRouter

class RunTestViewModel(
    private val addAnswerUseCase: AddAnswerUseCase,
    private val runTestRouter: RunTestRouter
) : ViewModel() {
    private lateinit var testBody: TestBody
    var mustBeClicked: Int = 0
    var nowClicked = MutableLiveData<Int>(0)

    fun setTestBody(testBody: TestBody) {
        this.testBody = testBody
    }

    fun getTestBody(): TestBody = testBody

    private fun mapTestBodyToTestResult(testBody: TestBody): TestResult {
        return TestResult(
            testBody.hashCode,
            testBody.items,
            FirebaseAuth.getInstance().currentUser!!.email!!
        )
    }

    suspend fun sendAnswer(): Boolean {
        return addAnswerUseCase.execute(mapTestBodyToTestResult(testBody))
    }

    fun goBack() {
        runTestRouter.goBack()
    }
}