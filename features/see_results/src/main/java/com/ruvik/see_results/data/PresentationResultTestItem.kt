package com.ruvik.see_results.data

/**
 * Data class representing a presentation item in the result of a test.
 *
 * @param title The title or question of the test item.
 * @param variants The list of answer options for the test item.
 * @param answers The list of selected answers by the user, represented as pairs of (index, count).
 */
data class PresentationResultTestItem(
    val title: String,
    val variants: List<String>,
    val answers: MutableList<Pair<Int, Int>>
)
