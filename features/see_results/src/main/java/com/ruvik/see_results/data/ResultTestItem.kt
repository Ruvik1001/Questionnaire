package com.ruvik.see_results.data

data class ResultTestItem(
    val title: String,
    val variants: List<String>,
    val answers: MutableList<Int>
)