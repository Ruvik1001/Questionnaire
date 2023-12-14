package com.ruvik.run_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ruvik.common.DataTime
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import com.ruvik.domain.testmanagement.usecase.AddAnswerUseCase
import com.ruvik.run_test.RunTestRouter

/**
 * ViewModel for the RunTest feature.
 *
 * @param addAnswerUseCase The use case for adding answers to a test.
 * @param runTestRouter The router for navigation within the RunTest feature.
 */
class RunTestViewModel(
    private val addAnswerUseCase: AddAnswerUseCase,
    private val runTestRouter: RunTestRouter
) : ViewModel() {
    private lateinit var testBody: TestBody
    var mustBeClicked: Int = 0
    var nowClicked = MutableLiveData<Int>(0)

    /**
     * Sets the test body for the current test.
     *
     * @param testBody The TestBody object representing the test.
     */
    fun setTestBody(testBody: TestBody) {
        this.testBody = testBody
    }

    /**
     * Retrieves the current test body.
     *
     * @return The TestBody object representing the test.
     */
    fun getTestBody(): TestBody = testBody

    /**
     * Maps the TestBody object to a TestResult object.
     *
     * @param testBody The TestBody object representing the test.
     * @return The TestResult object representing the test result.
     */
    private fun mapTestBodyToTestResult(testBody: TestBody): TestResult {
        return TestResult(
            testBody.hashCode,
            testBody.items,
            FirebaseAuth.getInstance().currentUser!!.email!!
        )
    }

    /**
     * Submits the answers for the current test.
     *
     * @return True if the answers are successfully submitted, false otherwise.
     */
    suspend fun sendAnswer(): Boolean {
        return addAnswerUseCase.execute(mapTestBodyToTestResult(testBody))
    }

    /**
     * Navigates back to the previous screen.
     */
    fun goBack() {
        runTestRouter.goBack()
    }
}
