package com.ruvik.my_tests

/**
 * Router interface for navigation related to user's tests.
 */
interface MyTestsRouter {
    /**
     * Navigates to the screen for creating a new test.
     */
    fun goToCreateNewTest()

    /**
     * Navigates to the screen to see the results of a specific test.
     *
     * @param testHashCode The hash code of the test.
     */
    fun goToSeeTestResults(testHashCode: String)

    /**
     * Navigates to the screen for finding tests.
     */
    fun goToFindTest()
}
