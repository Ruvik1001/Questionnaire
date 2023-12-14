package com.ruvik.see_results.data

data class PresentationResultTestItem(
    val title: String,
    val variants: List<String>,
    val answers: MutableList<Pair<Int, Int>>
)