package com.ruvik.domain.testmanagement.data.testinfo

/**
 * Data class representing the body of a test.
 *
 * @property hashCode The unique hash code of the test.
 * @property name The name or title of the test.
 * @property author The author or creator of the test.
 * @property items The list of test items/questions in the test.
 */
data class TestBody(
    var hashCode: String = "",
    var name: String = "",
    var author: String = "",
    var items: List<TestItem> = listOf()
)
