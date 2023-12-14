package com.ruvik.domain.testmanagement.data.testinfo

data class TestItem(
    var title: String = "",
    var itemType: Int = 0,
    var variants: List<String> = listOf(),
    var changed: List<Int> = listOf()
)
