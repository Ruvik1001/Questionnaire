package com.ruvik.domain.testmanagement.data

import com.ruvik.domain.testmanagement.data.testinfo.TestBody

data class User(
    var email: String = "",
    var tests: List<TestBody>? = null
)