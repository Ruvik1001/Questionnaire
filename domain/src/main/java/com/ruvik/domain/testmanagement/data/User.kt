package com.ruvik.domain.testmanagement.data

import com.ruvik.domain.testmanagement.data.testinfo.TestBody

/**
 * Data class representing a user in the context of test management.
 *
 * @property email The email address associated with the user.
 * @property tests The list of TestBody objects representing tests associated with the user.
 */
data class User(
    var email: String = "",
    var tests: List<TestBody>? = null
)
