package com.ruvik.domain.testmanagement.data.testinfo

data class TestBody(
    var hashCode: String = "",
    var name: String = "",
    var author: String = "",
    var items: List<TestItem> = listOf()
)
