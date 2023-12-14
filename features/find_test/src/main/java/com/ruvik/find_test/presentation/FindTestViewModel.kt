package com.ruvik.find_test.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.usecase.FindTestByHashCodeUseCase
import com.ruvik.find_test.FindTestRouter

/**
 * ViewModel for finding tests.
 *
 * @param findTestByHashCodeUseCase The use case for finding a test by its hash code.
 * @param findTestRouter The router for navigating to different screens within the Find Test feature.
 */
class FindTestViewModel(
    private val findTestByHashCodeUseCase: FindTestByHashCodeUseCase,
    private val findTestRouter: FindTestRouter
) : ViewModel() {
    private var testBody: TestBody? = null

    /**
     * Finds a test based on its hash code.
     *
     * @param hashCode The hash code of the test to find.
     * @return `true` if the test is found, `false` otherwise.
     */
    suspend fun findTest(hashCode: String): Boolean {
        testBody = findTestByHashCodeUseCase.execute(hashCode)
        return testBody != null
    }

    /**
     * Initiates the process of running the found test.
     *
     * @return `true` if the test is valid and the navigation is successful, `false` otherwise.
     */
    fun runTest(): Boolean = if (testBody == null) false else {
        findTestRouter.goToRunTest(testBody!!)
        true
    }

    /**
     * Retrieves metadata about the found test.
     *
     * @return A pair of strings representing the author and name of the test.
     */
    fun getMetadata(): Pair<String, String> = Pair(testBody?.author ?: "", testBody?.name ?: "")

    /**
     * Navigates to the "My Tests" screen.
     */
    fun goToMyTests() {
        findTestRouter.goToMyTests()
    }
}
