package com.ruvik.see_results.data

/**
 * Data class representing an item in the result of a test.
 *
 * @param title The title or question of the test item.
 * @param variants The list of answer options for the test item.
 * @param answers The list of selected answers by the user, represented as indices.
 */
data class ResultTestItem(
    val title: String,
    val variants: List<String>,
    val answers: MutableList<Int>
)
