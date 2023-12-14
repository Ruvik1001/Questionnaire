package com.ruvik.domain.testmanagement.data.testinfo

/**
 * Data class representing the result of a test submitted by a user.
 *
 * @property hashCode The unique identifier or hash code associated with the test result.
 * @property answers The list of TestItems representing the user's answers to the test.
 * @property user The identifier of the user who submitted the test result.
 */
data class TestResult(
    var hashCode: String = "",
    var answers: List<TestItem> = listOf(),
    var user: String = ""
)
