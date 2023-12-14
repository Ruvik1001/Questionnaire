package com.ruvik.find_test

import com.ruvik.domain.testmanagement.data.testinfo.TestBody

/**
 * Router interface for navigating from the Find Test screen.
 */
interface FindTestRouter {
    /**
     * Navigates to the My Tests screen.
     */
    fun goToMyTests()

    /**
     * Navigates to the Run Test screen with the provided test body.
     *
     * @param testBody The test body for the selected test.
     */
    fun goToRunTest(testBody: TestBody)
}
