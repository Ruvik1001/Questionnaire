package com.ruvik.domain.testmanagement.data.testinfo

/**
 * Data class representing an item/question within a test.
 *
 * @property title The title or text of the item/question.
 * @property itemType The type of the item/question (e.g., multiple-choice, true/false).
 * @property variants The list of answer options or variants for the item/question.
 * @property changed The list indicating which variants have been changed or modified.
 */
data class TestItem(
    var title: String = "",
    var itemType: Int = 0,
    var variants: List<String> = listOf(),
    var changed: List<Int> = listOf()
)
