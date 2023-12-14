package com.ruvik.domain.testmanagement.data.testinfo

data class TestResult(
    var hashCode: String = "",
    var answers: List<TestItem> = listOf(),
    var user: String = ""
)
